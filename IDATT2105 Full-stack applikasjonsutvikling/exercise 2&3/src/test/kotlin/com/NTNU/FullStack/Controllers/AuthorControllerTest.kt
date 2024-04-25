package com.NTNU.FullStack.Controllers

import com.NTNU.FullStack.Model.Author
import com.NTNU.FullStack.Repositories.AuthorRepository
import com.NTNU.FullStack.factories.AuthorFactory
import com.NTNU.FullStack.utils.getRandomString
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    private val URL = "/api/author/"

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mvc: MockMvc

    @Autowired
    private lateinit var authorRepo: AuthorRepository


    private lateinit var author: Author

    @BeforeEach
    fun setUp(){
        author = AuthorFactory().`object`
        author = authorRepo.save(author)
    }
    @Test
    fun `test author controller GET returns OK and the author`() {
        this.mvc.perform(get("$URL{name}", author.name))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("\$.name").value(author.name))
                .andExpect(jsonPath("\$.age").value(author.age))

    }

    @Test
    fun `test author controller GET returns not found`() {
        this.mvc.perform(get("$URL{name}","test"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `test author controller POST returns OK`() {
        val newAuthor = AuthorFactory().`object`
        this.mvc.perform(post(URL)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(newAuthor)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.name").value(newAuthor.name))
                .andExpect(jsonPath("\$.age").value(newAuthor.age))
    }

    @Test
    fun `test author controller PUT returns OK`() {
        val newName = getRandomString(5)
        val name = author.name
        author.name = newName
        this.mvc.perform(put("$URL{name}/", name)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isOk)
                .andExpect(jsonPath("\$.name").value(newName))
                .andExpect(jsonPath("\$.age").value(author.age))
    }

    @Test
    fun `test author controller PUT returns not found`() {
        this.mvc.perform(put("$URL{name}/","test")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(author)))
                .andExpect(status().isNotFound)
    }

    @Test
    fun `test author controller DELETE returns OK`() {
        this.mvc.perform(delete("$URL{name}/", author.name))
                .andExpect(status().isOk)
    }

    @Test
    fun `test author controller DELETE return NotFound`() {
        this.mvc.perform(delete("$URL{name}/", getRandomString(5)))
                .andExpect(status().isNotFound)
    }
}