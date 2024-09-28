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

    @PutMapping("/{id}")
    fun updateAuthor(@PathVariable("id") id: String, @RequestBody req: UpdateAuthorRequest): ResponseEntity<Author?> {
        val current = repository.findById(id) ?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        val updated = current.update(req.name, req.birthday)
        updated.fold(
            onSuccess = {
                repository.save(it)
                return ResponseEntity(it, HttpStatus.OK)
            }, onFailure = {
                return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            })
    }

    @PostMapping("")
    fun createNewAuthor(@RequestBody req: CreateAuthorRequest): ResponseEntity<Author?> {
        val newAuthor = Author.from(req.name, req.birthday)
        newAuthor.fold(
            onSuccess = {
                repository.save(it)
                return ResponseEntity(it, HttpStatus.OK)
            }, onFailure = {
                return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            })
    }
}
