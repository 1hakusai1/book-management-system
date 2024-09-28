package com.example.bookmanagementsystem.book

data class CreateBookRequest(
    val title: String,
    val authorIds: List<String>,
    val price: Int,
    val publicationStatus: PublicationStatus
)
