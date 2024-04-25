package com.NTNU.FullStack.Model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull





@Entity
@Table(name = "book")
data class Book(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                @NotNull
                  var id: Long,
                @NotNull
                @Column(unique = true)
                  var name:String,
                @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
                @JoinTable(
                        name = "author_book",
                        joinColumns = [JoinColumn(name = "book_id", referencedColumnName = "id")],
                        inverseJoinColumns = [JoinColumn(name = "author_id", referencedColumnName = "id")],
                        uniqueConstraints = [UniqueConstraint(columnNames = ["book_id", "author_id"])]
                )
                @JsonBackReference
                @JsonIgnore
                var authors: MutableList<Author> = mutableListOf()
)
data class BookList(val id: Long, val name: String)

fun Book.toBookList(): BookList {
    return BookList(
            this.id,
            this.name
    )
}

data class BookResponse(val id: Long, val name: String,val books: List<AuthorList> )

fun Book.toBookResponse(): BookResponse {
    return BookResponse(
            this.id,
            this.name,
            this.authors.map { author -> author.toAuthorList() },
    )
}