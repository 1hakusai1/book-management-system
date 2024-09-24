package com.example.bookmanagementsystem.book

import com.example.bookmanagementsystem.jooq.public_.tables.AuthorsBooks.AUTHORS_BOOKS
import com.example.bookmanagementsystem.jooq.public_.tables.Books.BOOKS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class BookRepository(val dslContext: DSLContext) {
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
