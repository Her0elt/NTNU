package com.example.exercise3.friends

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FriendState : ViewModel() {

    var friends = mutableStateListOf<Friend>()
        private set

    fun addItem(name: String, birthDate: String) {
        val id = friends.size + 1
        friends.add(Friend(id, name, birthDate))
    }

    fun updateFriend(id: Int, name: String, birthdate: String) {
        friends.filter { it.id == id }.forEach {
            it.name = name
            it.birthDate = birthdate
        }
    }
}