package com.NTNU.FullStack.Repositories

import com.NTNU.FullStack.Model.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : JpaRepository<Book, Long> {
    override fun findAll(page: Pageable): Page<Book>
    fun findBookByName(name: String?): Book?
    fun findBooksByNameContains(name: String?, page: Pageable): List<Book>?
    fun findByAuthors_NameContains(name: String?): List<Book>
    fun findByNameContainingAndAuthors_NameContains(name :String?, authorName:String?): List<Book>
}