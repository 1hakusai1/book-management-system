package com.example.bookmanagementsystem.book

import com.fasterxml.uuid.Generators

enum class PublicationStatus(val value: String) {
    UNPUBLISHED("unpublished"),
    PUBLISHED("published")
}

data class Book(
    val id: String,
    val title: String,
    val price: Int,
    val publicationStatus: PublicationStatus,
    val authorIds: List<String>,
) {
    companion object {
        fun from(title: String, price: Int, publicationStatus: PublicationStatus, authorIds: List<String>): Book {
            val id = Generators.timeBasedGenerator().generate().toString()
            return Book(id, title, price, publicationStatus, authorIds)
        }
    }
}
