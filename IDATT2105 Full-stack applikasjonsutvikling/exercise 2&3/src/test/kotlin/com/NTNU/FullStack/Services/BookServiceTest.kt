package com.NTNU.FullStack.Services


import com.NTNU.FullStack.Model.Book
import com.NTNU.FullStack.Repositories.BookRepository
import com.NTNU.FullStack.factories.AuthorFactory
import com.NTNU.FullStack.factories.BookFactory
import com.NTNU.FullStack.utils.getRandomString
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class BookServiceTest {

    @InjectMocks
    private lateinit var bookService: BookService

    @Mock
    private lateinit var bookRepo: BookRepository

    private lateinit var book: Book


    @BeforeEach
    fun setUp(){
        book = BookFactory().`object`
        Mockito.lenient().`when`(bookRepo.findBookByName(book.name)).thenReturn(book)
        Mockito.lenient().`when`(bookRepo.findAll()).thenReturn(arrayListOf(BookFactory().`object`, BookFactory().`object`))
    }
    @Test
    fun `test book service get by name returns book`(){
        assert(this.bookService.getBookByName(book.name).id == book.id)
    }

    @Test
    fun `test book service create book creates and returns book`(){
        val newBook = BookFactory().`object`
        Mockito.lenient().`when`(bookRepo.save(newBook)).thenReturn(newBook)
        assert(this.bookService.createNewBook(newBook).name == newBook.name)
    }

    @Test
    fun `test book service update author updates and returns book`(){
        val newName = getRandomString(8)
        val name = book.name
        book.name = newName
        Mockito.lenient().`when`(bookRepo.save(book)).thenReturn(book)
        assert(this.bookService.updateBookByName(name, book).name == newName)
    }

    @Test
    fun `test book service delete author deletes and returns true`(){
        assert(this.bookService.deleteBookByName(book.name))
    }
}