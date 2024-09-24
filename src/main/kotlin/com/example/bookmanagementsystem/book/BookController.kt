package com.example.bookmanagementsystem.book

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController(val bookRepository: BookRepository) {

    @PostMapping("")
    fun createNewBook(@RequestBody req: CreateBookRequest): Book {
        val newBook = Book.from(req.title, req.authorIds)
        bookRepository.save(newBook)
        return newBook
    }
}
