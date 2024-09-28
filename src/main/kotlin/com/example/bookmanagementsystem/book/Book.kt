package com.example.bookmanagementsystem.book

import com.example.bookmanagementsystem.ValidationException
import com.fasterxml.uuid.Generators

enum class PublicationStatus(val value: String) {
    UNPUBLISHED("unpublished"),
    PUBLISHED("published")
}

class Book(
    val id: String,
    val title: String,
    val price: Int,
    val publicationStatus: PublicationStatus,
    val authorIds: List<String>,
) {
    init {
        if (price < 0) throw ValidationException("書籍の価格が0未満です。id: $id, price: $price")
    }

    fun update(
        newTitle: String,
        newPrice: Int,
        newPublicationStatus: PublicationStatus,
        newAuthorIds: List<String>,
    ): Result<Book> {
        return kotlin.runCatching {
            if (publicationStatus == PublicationStatus.PUBLISHED && newPublicationStatus == PublicationStatus.UNPUBLISHED)
                throw ValidationException("出版済みの書籍を未出版に変更することはできません。id: $id")
            Book(id, newTitle, newPrice, newPublicationStatus, newAuthorIds)
        }
    }

    companion object {
        fun from(
            title: String,
            price: Int,
            publicationStatus: PublicationStatus,
            authorIds: List<String>
        ): Result<Book> {
            val id = Generators.timeBasedGenerator().generate().toString()
            return kotlin.runCatching { Book(id, title, price, publicationStatus, authorIds) }
        }
    }
}
