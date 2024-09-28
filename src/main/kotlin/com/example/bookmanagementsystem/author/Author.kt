package com.example.bookmanagementsystem.author

import com.fasterxml.uuid.Generators
import java.time.LocalDate

data class Author(val id: String, val name: String, val birthday: LocalDate?) {
    companion object {
        fun from(name: String, birthday: LocalDate?): Author {
            val id = Generators.timeBasedGenerator().generate().toString()
            return Author(id, name, birthday)
        }
    }
}
