package com.example.bookmanagementsystem.book

import com.example.bookmanagementsystem.jooq.public_.tables.AuthorsBooks.AUTHORS_BOOKS
import com.example.bookmanagementsystem.jooq.public_.tables.Books.BOOKS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BookRepository(val dslContext: DSLContext) {
    fun findById(id: String): Book? {
        val books = dslContext
            .select(BOOKS.ID, BOOKS.TITLE, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .join(AUTHORS_BOOKS).on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID).and(BOOKS.ID.eq(UUID.fromString(id))))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map {
                Book(
                    id = it.key.toString(),
                    title = it.value.first().getValue(BOOKS.TITLE),
                    authorIds = it.value.map { record ->
                        record.getValue(AUTHORS_BOOKS.AUTHOR_ID).toString()
                    })
            }
        return if (books.isEmpty()) null else books.first()
    }

    fun findByAuthorId(authorId: String): List<Book> {
        return dslContext
            .select(BOOKS.ID, BOOKS.TITLE, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .join(AUTHORS_BOOKS)
            .on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID).and(AUTHORS_BOOKS.AUTHOR_ID.eq(UUID.fromString(authorId))))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map {
                Book(
                    id = it.key.toString(),
                    title = it.value.first().getValue(BOOKS.TITLE),
                    authorIds = it.value.map { record ->
                        record.getValue(AUTHORS_BOOKS.AUTHOR_ID).toString()
                    })
            }
    }

    fun list(): List<Book> {
        return dslContext
            .select(BOOKS.ID, BOOKS.TITLE, AUTHORS_BOOKS.AUTHOR_ID)
            .from(BOOKS)
            .join(AUTHORS_BOOKS).on(BOOKS.ID.eq(AUTHORS_BOOKS.BOOK_ID))
            .fetch()
            .groupBy { it.getValue(BOOKS.ID) }
            .map {
                Book(
                    id = it.key.toString(),
                    title = it.value.first().getValue(BOOKS.TITLE),
                    authorIds = it.value.map { record ->
                        record.getValue(AUTHORS_BOOKS.AUTHOR_ID).toString()
                    })
            }
    }

    fun save(book: Book) {
        dslContext.transaction { c ->
            c.dsl().newRecord(BOOKS).also {
                it.id = UUID.fromString(book.id)
                it.title = book.title
                it.store()
            }
            book.authorIds.forEach { authorId ->
                c.dsl().newRecord(AUTHORS_BOOKS).also {
                    it.bookId = UUID.fromString(book.id)
                    it.authorId = UUID.fromString(authorId)
                    it.store()
                }
            }
        }
    }
}
