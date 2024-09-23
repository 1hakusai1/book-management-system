package com.example.bookmanagementsystem.author

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/author")
class AuthorController(val repository: AuthorRepository) {
    @GetMapping("")
    fun listAuthors(): Map<String, String> {
        return mapOf("name" to "dummy")
    }

    @PostMapping("")
    fun createNewAuthor(@RequestBody req: CreateAuthorRequest): Author {
        val newAuthor = Author.from(req.name)
        repository.save(newAuthor)
        return newAuthor
    }
}
