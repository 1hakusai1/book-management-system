package com.example.bookmanagementsystem.author

import com.example.bookmanagementsystem.ValidationException
import com.fasterxml.uuid.Generators
import java.time.Clock
import java.time.LocalDate

class Author(val id: String, val name: String, val birthday: LocalDate?) {
    init {
        val today = LocalDate.now(clock)
        if (birthday != null && !birthday.isBefore(today))
            throw ValidationException("著者の誕生日が現在の日付以降になっています。id: $id, birthday: $birthday, today: $today")
    }

    fun update(newName: String, newBirthday: LocalDate?): Result<Author> {
        return kotlin.runCatching { Author(id, newName, newBirthday) }
    }

    companion object {
        // visible for testing
        internal var clock = Clock.systemDefaultZone()

        fun from(name: String, birthday: LocalDate?): Result<Author> {
            val id = Generators.timeBasedGenerator().generate().toString()
            return kotlin.runCatching { Author(id, name, birthday) }
        }
    }
}
