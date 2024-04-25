package com.NTNU.FullStack.factories

import com.NTNU.FullStack.Model.Adress
import com.NTNU.FullStack.utils.getRandomString
import org.springframework.beans.factory.FactoryBean
import kotlin.random.Random

class AdressFactory : FactoryBean<Adress> {

    override fun getObjectType(): Class<*> {
        return Adress::class.java
    }

    override fun isSingleton(): Boolean {
        return false
    }

    override fun getObject(): Adress {
        return Adress(0, getRandomString(10), Random(1).nextInt(),  getRandomString(10), null)
    }
}