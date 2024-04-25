package com.NTNU.FullStack.Controllers

import com.NTNU.FullStack.Exception.AuthorNotFoundException
import com.NTNU.FullStack.Model.*
import com.NTNU.FullStack.Services.AuthorService
import com.NTNU.FullStack.utils.ErrorResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid


@RestController
@RequestMapping("/api/author")
class AuthorController {

    @Autowired
    private lateinit var authorService: AuthorService

    @GetMapping
    fun getAll(pageable:Pageable): Page<AuthorList>{
        println(pageable.sort)
        return authorService.getAllAuthors(pageable);
    }

    @GetMapping("/{authorName}")
    fun get(@PathVariable authorName: String): ResponseEntity<*> {
        try {
            return ResponseEntity.ok(authorService.getAuthorByName(authorName).toAuthorResponse())
        }catch (e: AuthorNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body<Any>(e.message?.let { ErrorResponse(it) })
        }
    }

    @PostMapping("/")
    fun create(@RequestBody newAuthor: Author): ResponseEntity<*> {
        try {
            return ResponseEntity.ok(authorService.createNewAuthor(newAuthor).toAuthorResponse())
        }catch (e: AuthorNotFoundException){
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Author Not Found", e)
        }
    }

    @PutMapping("/{authorName}/")
    fun update(@PathVariable authorName: String, @Valid @RequestBody newAuthor: Author): ResponseEntity<*> {
        try {
            return ResponseEntity.ok(authorService.updateAuthorByName(authorName,newAuthor).toAuthorResponse())
        }catch (e: AuthorNotFoundException){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body<Any>(e.message)
        }
    }

    @DeleteMapping("/{authorName}/")
    fun delete(@PathVariable authorName: String): ResponseEntity<*> {
        try {
            return ResponseEntity.ok(authorService.deleteAuthorByName(authorName))
        }catch (e: AuthorNotFoundException){
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Author Not Found", e)
        }
    }
}