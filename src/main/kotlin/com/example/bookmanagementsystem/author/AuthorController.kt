package com.example.bookmanagementsystem.author

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/author")
class AuthorController {
    @GetMapping("")
    fun listAuthors(): Map<String, String> {
        return mapOf("name" to "dummy")
    }
}
