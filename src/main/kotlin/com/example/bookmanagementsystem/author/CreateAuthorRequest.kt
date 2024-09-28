package com.example.bookmanagementsystem.author

import java.time.LocalDate

data class CreateAuthorRequest(val name: String, val birthday: LocalDate?)
