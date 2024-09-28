package com.example.bookmanagementsystem.book

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BookTest {
    @Test
    @DisplayName("価格が0未満の場合はエラーになる")
    fun testPriceValidation() {
        val created =
            Book.from("Alice's Adventures in Wonderland", -200, PublicationStatus.PUBLISHED, listOf())
        assert(created.isFailure)
    }

    @Test
    @DisplayName("出版済みの本を未出版に戻すことはできない")
    fun testPublicationStatus() {
        val book = Book.from("Brave New World", 2000, PublicationStatus.PUBLISHED, listOf()).getOrThrow()
        val updated = book.update("Brave New World", 2000, PublicationStatus.UNPUBLISHED, listOf())
        assert(updated.isFailure)
    }
}
