package com.NTNU.FullStack.Services

import com.NTNU.FullStack.Model.Author
import com.NTNU.FullStack.Model.AuthorList
import com.NTNU.FullStack.Repositories.AuthorRepository
import com.NTNU.FullStack.factories.AuthorFactory
import com.NTNU.FullStack.utils.getRandomString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class AuthorServiceTest {

    @InjectMocks
    private lateinit var authorService: AuthorService

    @Mock
    private lateinit var authorRepo: AuthorRepository

    private lateinit var author: Author


    @BeforeEach
    fun setUp(){
        author = AuthorFactory().`object`
        Mockito.lenient().`when`(authorRepo.findAuthorByName(author.name)).thenReturn(author)
        Mockito.lenient().`when`(authorRepo.findAll()).thenReturn(arrayListOf(AuthorFactory().`object`, AuthorFactory().`object`))
    }
    @Test
    fun `test author service get all returns list of authors`(){
        assert(this.authorService.getAllAuthors().isNotEmpty())
    }

    @Test
    fun `test author service get by name returns author`(){
        assert(this.authorService.getAuthorByName(author.name).id.equals(author.id))
    }

    @Test
    fun `test author service_create author creates and returns author`(){
        val newAuthor = AuthorFactory().`object`
        Mockito.lenient().`when`(authorRepo.save(newAuthor)).thenReturn(newAuthor)
        assert(this.authorService.createNewAuthor(newAuthor).name == newAuthor.name)
    }

    @Test
    fun `test author service update author updates and returns author`(){
        val name = author.name
        val newName = getRandomString(8)
        author.name = newName
        Mockito.lenient().`when`(authorRepo.save(author)).thenReturn(author)
        assert(this.authorService.updateAuthorByName(name, author).name == newName)
    }

    @Test
    fun `test author service delete author deletes and returns true`(){
        assert(this.authorService.deleteAuthorByName(author.name))
    }


}