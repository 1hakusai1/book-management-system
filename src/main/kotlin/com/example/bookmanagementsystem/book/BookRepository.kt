package com.example.bookmanagementsystem.book

import com.example.bookmanagementsystem.jooq.public_.tables.AuthorsBooks.AUTHORS_BOOKS
import com.example.bookmanagementsystem.jooq.public_.tables.Books.BOOKS
import org.jooq.DSLContext
import org.jooq.Record5
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository
import java.util.*
import com.example.bookmanagementsystem.jooq.public_.enums.PublicationStatus as JooqPublicationStatus

@Repository
class BookRepository(val dslContext: DSLContext) {
    fun findById(id: String): Book? {
        val books = dslContext
            .select(BOOKS.ID, BOOKS.TITLE, BOOKS.PRICE, BOOKS.PUBLICATION_STATUS, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .leftOuterJoin(AUTHORS_BOOKS).on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID))
            .where(BOOKS.ID.eq(UUID.fromString(id)))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map { toBook(it) }
        return if (books.isEmpty()) null else books.first()
    }

    private fun toBook(it: Map.Entry<UUID, List<Record5<UUID, String, Int, com.example.bookmanagementsystem.jooq.public_.enums.PublicationStatus, UUID>>>) =
        Book(
            id = it.key.toString(),
            title = it.value.first().getValue(BOOKS.TITLE),
            authorIds = it.value
                .filter { record -> record.getValue(AUTHORS_BOOKS.AUTHOR_ID) != null }
                .map { record ->
                    record.getValue(AUTHORS_BOOKS.AUTHOR_ID).toString()
                },
            price = it.value.first().getValue(BOOKS.PRICE),
            publicationStatus = when (it.value.first().getValue(BOOKS.PUBLICATION_STATUS)) {
                JooqPublicationStatus.unpublished -> PublicationStatus.UNPUBLISHED
                JooqPublicationStatus.published -> PublicationStatus.PUBLISHED
                null -> throw Exception("本来nullになるはずのない部分でnullが返っています")
            }
        )

    fun findByAuthorId(authorId: String): List<Book> {
        return dslContext
            .select(BOOKS.ID, BOOKS.TITLE, BOOKS.PRICE, BOOKS.PUBLICATION_STATUS, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .leftOuterJoin(AUTHORS_BOOKS).on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID))
            .where(AUTHORS_BOOKS.AUTHOR_ID.eq(UUID.fromString(authorId)))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map { toBook(it) }
    }

    fun list(): List<Book> {
        return dslContext
            .select(BOOKS.ID, BOOKS.TITLE, BOOKS.PRICE, BOOKS.PUBLICATION_STATUS, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .leftOuterJoin(AUTHORS_BOOKS).on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map { toBook(it) }
    }

    fun save(book: Book) {
        val status = when (book.publicationStatus) {
            PublicationStatus.UNPUBLISHED -> JooqPublicationStatus.unpublished
            PublicationStatus.PUBLISHED -> JooqPublicationStatus.published
        }

        dslContext.transaction { c ->
            c.dsl()
                .insertInto(BOOKS, BOOKS.ID, BOOKS.TITLE, BOOKS.PRICE, BOOKS.PUBLICATION_STATUS)
                .values(UUID.fromString(book.id), book.title, book.price, status)
                .onDuplicateKeyUpdate()
                .set(BOOKS.TITLE, book.title)
                .set(BOOKS.PRICE, book.price)
                .set(BOOKS.PUBLICATION_STATUS, status)
                .execute()
            c.dsl()
                .deleteFrom(AUTHORS_BOOKS)
                .where(AUTHORS_BOOKS.BOOK_ID.eq(UUID.fromString(book.id)))
                .execute()
            c.dsl()
                .insertInto(AUTHORS_BOOKS, AUTHORS_BOOKS.AUTHOR_ID, AUTHORS_BOOKS.BOOK_ID)
                .valuesOfRows(book.authorIds.map { DSL.row(UUID.fromString(it), UUID.fromString(book.id)) })
                .execute()
        }
    }
}
