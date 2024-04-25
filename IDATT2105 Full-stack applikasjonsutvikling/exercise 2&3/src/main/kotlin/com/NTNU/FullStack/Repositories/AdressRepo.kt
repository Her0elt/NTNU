package com.NTNU.FullStack.Repositories

import com.NTNU.FullStack.Model.Adress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdressRepository : JpaRepository<Adress, Long> {
    fun findAdressById(id: Long?): Adress?
}