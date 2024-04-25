package com.NTNU.FullStack.Repositories

import com.NTNU.FullStack.Model.Author
import com.NTNU.FullStack.Model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<Author, Long> {
    fun findAuthorByName(name: String?): Author?
    override fun findAll(page: Pageable): Page<Author>
}