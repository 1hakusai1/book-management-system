package com.example.bookmanagementsystem.author

import java.time.LocalDate

data class UpdateAuthorRequest(val name: String, val birthday: LocalDate?)
