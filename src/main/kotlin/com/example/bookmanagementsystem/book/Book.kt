package com.example.bookmanagementsystem.book

import com.fasterxml.uuid.Generators

data class Book(val id: String, val title: String, val authorIds: List<String>) {
    companion object {
        fun from(title: String, authorIds: List<String>): Book {
            val id = Generators.timeBasedGenerator().generate().toString()
            return Book(id, title, authorIds)
        }
    }
}
