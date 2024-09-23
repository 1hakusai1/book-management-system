package com.example.bookmanagementsystem.author

import com.fasterxml.uuid.Generators

data class Author(val id: String, val name: String) {
    companion object {
        fun from(name: String): Author {
            val id = Generators.timeBasedGenerator().generate().toString()
            return Author(id, name)
        }
    }
}
