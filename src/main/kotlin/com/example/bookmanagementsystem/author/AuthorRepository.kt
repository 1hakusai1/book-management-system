package com.example.bookmanagementsystem.author

import com.example.bookmanagementsystem.jooq.public_.tables.Authors.AUTHORS
import org.jooq.DSLContext
import org.jooq.Record3
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
class AuthorRepository(val dslContext: DSLContext) {

    fun list(): List<Author> {
        val response = dslContext
            .select(AUTHORS.ID, AUTHORS.NAME, AUTHORS.BIRTHDAY)
            .from(AUTHORS)
            .fetch()
        return response.map { toAuthor(it) }
    }

    fun findById(id: String): Author? {
        val response = dslContext
            .select(AUTHORS.ID, AUTHORS.NAME, AUTHORS.BIRTHDAY)
            .from(AUTHORS)
            .where(AUTHORS.ID.eq(UUID.fromString(id)))
            .fetchOne()
        return response?.let { toAuthor(it) }
    }

    private fun toAuthor(response: Record3<UUID, String, LocalDate>) = Author(
        response.getValue(AUTHORS.ID).toString(),
        response.getValue(AUTHORS.NAME),
        response.getValue(AUTHORS.BIRTHDAY)
    )

    fun save(author: Author) {
        dslContext
            .insertInto(AUTHORS, AUTHORS.ID, AUTHORS.NAME, AUTHORS.BIRTHDAY)
            .values(UUID.fromString(author.id), author.name, author.birthday)
            .onDuplicateKeyUpdate()
            .set(AUTHORS.NAME, author.name)
            .set(AUTHORS.BIRTHDAY, author.birthday)
            .execute()
    }

}
