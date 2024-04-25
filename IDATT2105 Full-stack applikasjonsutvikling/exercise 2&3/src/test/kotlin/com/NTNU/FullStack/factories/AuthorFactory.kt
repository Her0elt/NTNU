package com.NTNU.FullStack.factories

import com.NTNU.FullStack.Model.Author
import com.NTNU.FullStack.utils.getRandomString
import org.springframework.beans.factory.FactoryBean
import kotlin.random.Random


class AuthorFactory : FactoryBean<Author> {

    override fun getObjectType(): Class<*> {
        return Author::class.java
    }

    override fun isSingleton(): Boolean {
        return false
    }

    override fun getObject(): Author {
        return Author(0, getRandomString(10), Random(1).nextInt(), AdressFactory().`object`)
    }
}