package com.example.bookmanagementsystem.author

import com.example.bookmanagementsystem.jooq.public_.tables.Authors.AUTHORS
import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class AuthorRepository(val dslContext: DSLContext) {

    fun save(author: Author) {
        dslContext.newRecord(AUTHORS).also {
            it.id = UUID.fromString(author.id)
            it.name = author.name
            it.store()
        }
    }
}
