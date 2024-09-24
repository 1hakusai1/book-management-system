package com.example.bookmanagementsystem.author

import com.example.bookmanagementsystem.jooq.public_.tables.Authors.AUTHORS
import org.jooq.DSLContext
import org.jooq.Record2
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AuthorRepository(val dslContext: DSLContext) {

    fun list(): List<Author> {
        val response = dslContext
            .select(AUTHORS.ID, AUTHORS.NAME)
            .from(AUTHORS)
            .fetch()
        return response.map { toAuthor(it) }
    }

    fun findById(id: String): Author? {
        val response = dslContext
            .select(AUTHORS.ID, AUTHORS.NAME)
            .from(AUTHORS)
            .where(AUTHORS.ID.eq(UUID.fromString(id)))
            .fetchOne()
        return response?.let { toAuthor(it) }
    }

    private fun toAuthor(response: Record2<UUID, String>) = Author(
        response.getValue(AUTHORS.ID).toString(),
        response.getValue(AUTHORS.NAME)
    )

    fun save(author: Author) {
        dslContext.newRecord(AUTHORS).also {
            it.id = UUID.fromString(author.id)
            it.name = author.name
            it.store()
        }
    }
}
