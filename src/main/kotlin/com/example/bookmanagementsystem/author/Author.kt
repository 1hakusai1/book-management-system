package com.example.bookmanagementsystem.author

import com.example.bookmanagementsystem.ValidationException
import com.fasterxml.uuid.Generators
import java.time.LocalDate

class Author(val id: String, val name: String, val birthday: LocalDate?) {
    init {
        val today = LocalDate.now()
        if (birthday != null && !birthday.isBefore(today))
            throw ValidationException("著者の誕生日が現在の日付以降になっています。id: $id, birthday: $birthday, today: $today")
    }

    companion object {
        fun from(name: String, birthday: LocalDate?): Author {
            val id = Generators.timeBasedGenerator().generate().toString()
            return Author(id, name, birthday)
        }
    }
}
