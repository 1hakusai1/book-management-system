package com.example.bookmanagementsystem.book

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/book")
class BookController(val bookRepository: BookRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: String): ResponseEntity<Book?> {
        val found = bookRepository.findById(id)
        return if (found == null)
            ResponseEntity(null, HttpStatus.NOT_FOUND) else ResponseEntity(found, HttpStatus.OK)
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable("id") id: String, @RequestBody req: UpdateBookRequest): ResponseEntity<Book?> {
        val current = bookRepository.findById(id) ?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        val updated = current.copy(title = req.title, authorIds = req.authorIds)
        bookRepository.save(updated)
        return ResponseEntity(updated, HttpStatus.OK)
    }

    @GetMapping("")
    fun list(@RequestParam authorId: String?): List<Book> {
        return if (authorId == null) bookRepository.list() else bookRepository.findByAuthorId(authorId)
    }

    @PostMapping("")
    fun createNewBook(@RequestBody req: CreateBookRequest): Book {
        val newBook = Book.from(req.title, req.price, req.publicationStatus, req.authorIds)
        bookRepository.save(newBook)
        return newBook
    }
}
