package com.example.bookmanagementsystem.author


import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class AuthorTest {

    @AfterEach
    fun cleanUp() {
        Author.clock = Clock.systemDefaultZone()
    }

    @Test
    @DisplayName("現在の日付以前の日付を生年月日として設定することはできない")
    fun testAuthorBirthday() {
        Author.clock = Clock.fixed(Instant.parse("2023-01-01T00:00:00Z"), ZoneId.systemDefault())
        val created = Author.from("太宰治", LocalDate.of(2024, 1, 1))
        assert(created.isFailure)
    }
}
