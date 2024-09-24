package com.example.bookmanagementsystem.author

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/author")
class AuthorController(val repository: AuthorRepository) {
    @GetMapping("")
    fun listAuthors(): List<Author> {
        return repository.list()
    }

    @GetMapping("/{id}")
    fun findAuthor(@PathVariable("id") id: String): ResponseEntity<Author?> {
        val found = repository.findById(id)
        return if (found == null) {
            ResponseEntity(null, HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity(found, HttpStatus.OK)
        }
    }

    @PostMapping("")
    fun createNewAuthor(@RequestBody req: CreateAuthorRequest): Author {
        val newAuthor = Author.from(req.name)
        repository.save(newAuthor)
        return newAuthor
    }
}
