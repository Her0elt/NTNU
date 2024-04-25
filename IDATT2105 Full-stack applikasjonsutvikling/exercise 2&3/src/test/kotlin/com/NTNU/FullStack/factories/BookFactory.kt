package com.NTNU.FullStack.factories

import com.NTNU.FullStack.Model.Author
import com.NTNU.FullStack.Model.Book
import com.NTNU.FullStack.utils.getRandomString
import org.springframework.beans.factory.FactoryBean
import kotlin.random.Random

class BookFactory : FactoryBean<Book> {

    override fun getObjectType(): Class<*> {
        return Author::class.java
    }

    override fun isSingleton(): Boolean {
        return false
    }

    override fun getObject(): Book {
        return Book(0, getRandomString(10))
    }
}