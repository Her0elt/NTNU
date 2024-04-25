package com.NTNU.FullStack.Controllers


import com.NTNU.FullStack.Exception.BookNotFoundExecption
import com.NTNU.FullStack.Model.Book
import com.NTNU.FullStack.Repositories.BookRepository
import com.NTNU.FullStack.Services.BookService
import com.NTNU.FullStack.factories.BookFactory
import com.NTNU.FullStack.utils.getRandomString
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {


        private val URL = "/api/book/"

        @Autowired
        private lateinit var objectMapper: ObjectMapper

        @Autowired
        private lateinit var mvc: MockMvc

        @Autowired
        private lateinit var bookRepo: BookRepository

        private lateinit var book: Book


        @BeforeEach
        fun setUp(){
            book = BookFactory().`object`
            book = bookRepo.save(book)
        }
        @Test
        fun `test book controller GET return OK`() {
            this.mvc.perform(get("$URL{name}", book.name))
                    .andExpect(status().isOk)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("\$.name").value(book.name))
        }

        @Test
        fun `test book controller GET return NotFound`() {
            this.mvc.perform(get("$URL{name}", "test"))
                    .andExpect(status().isNotFound)

        }

        @Test
        fun `test book controller POST returns OK`() {
            val newBook = BookFactory().`object`
            this.mvc.perform(post(URL)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(newBook)))
                    .andExpect(status().isOk)
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("\$.name").value(newBook.name))
        }

        @Test
        fun `test book controller PUT returns OK`() {
            val newName = getRandomString(5)
            val name = book.name
            book.name = newName
            this.mvc.perform(put("$URL{name}/", name)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(book)))
                    .andExpect(status().isOk)
                    .andExpect(jsonPath("\$.name").value(newName))
        }

        @Test
        fun `test book controller PUT return NotFound`() {
            this.mvc.perform(put("$URL{name}/", "test")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(book)))
                    .andExpect(status().isNotFound)
        }

        @Test
        fun `test book controller DELETE returns OK`() {
            this.mvc.perform(delete("$URL{name}/", book.name))
                    .andExpect(status().isOk)
        }

        @Test
        fun `test book controller DELETE Return NotFound`() {
            this.mvc.perform(delete("$URL{name}/", getRandomString(8)))
                    .andExpect(status().isNotFound)
        }

}