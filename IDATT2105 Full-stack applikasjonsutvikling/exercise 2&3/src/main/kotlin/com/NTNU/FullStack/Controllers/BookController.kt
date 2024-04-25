package com.NTNU.FullStack.Controllers

import com.NTNU.FullStack.Model.*
import com.NTNU.FullStack.Services.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/book/")
class BookController {

    @Autowired
    private lateinit var bookService: BookService

    @GetMapping
    fun getAll(@RequestParam name: String?, @RequestParam authorName: String?,@RequestParam sort: Array<String>?, @RequestParam(value ="page", defaultValue = "0") page: Int?): ResponseEntity<*> {
        println(sort)
        return bookService.getAllBooks(name, authorName, sort , page!!)
    }

    @GetMapping("{bookName}")
    fun get(@PathVariable bookName: String): ResponseEntity<*> = ResponseEntity.ok(bookService.getBookByName(bookName))

    @PostMapping
    fun create(@Valid @RequestBody newBook: Book): ResponseEntity<*> = ResponseEntity.ok(bookService.createNewBook(newBook))

    @PutMapping("{bookName}/")
    fun update(@PathVariable bookName: String, @Valid @RequestBody newBook: Book): ResponseEntity<*> = ResponseEntity.ok(bookService.updateBookByName(bookName, newBook))

    @DeleteMapping("{bookName}/")
    fun delete(@PathVariable bookName: String): ResponseEntity<*> = ResponseEntity.ok(bookService.deleteBookByName(bookName))
}