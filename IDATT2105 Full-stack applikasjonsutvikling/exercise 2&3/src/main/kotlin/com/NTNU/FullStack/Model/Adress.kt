package com.NTNU.FullStack.Model

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull


@Entity
@Table(name = "adress")
data class Adress(@Id
                  @GeneratedValue(strategy = GenerationType.IDENTITY)
                  var id: Long,
                  @NotNull
                  var street:String,
                  @NotNull
                  var postCode:Int,
                  @NotNull
                  var country:String,
                  @OneToOne(mappedBy = "adress")
                  @JsonIgnore
                  @JsonBackReference
                  var author: Author?,
    )

data class AdressList(val id: Long, val street: String, val postCode:Int, val country: String)

fun Adress.toAdressList(): AdressList {
    return AdressList(
            this.id,
            this.street,
            this.postCode,
            this.country
    )
}