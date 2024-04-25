package com.example.exercise3.friends

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun FriendDetails(
    navController: NavController,
    friendId: Int,
    friends: List<Friend>,
    editFriend: (Int, String, String) -> Unit
){
    val friend = friends.find { it.id == friendId }
        ?: return Scaffold {
            Button(onClick = {
                navController.navigate("friends")
            }) {
                Text("Noe gikk galt, gå tilbake")
            }
        }
    val nameState = remember { mutableStateOf(TextFieldValue(text = friend.name)) }
    val birthdateState = remember { mutableStateOf(TextFieldValue(text = friend.birthDate)) }
    fun saveFriend() {
        editFriend(friendId, nameState.value.text, birthdateState.value.text)
        navController.navigate("friends")
    }
    Scaffold {
        Column(Modifier.padding(8.dp)) {
            TextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                label = { Text("Navn") })
            TextField(
                value = birthdateState.value,
                onValueChange = { birthdateState.value = it },
                label = { Text("Fødselsdag") })

            Button(onClick = { saveFriend() }) {
                Text("Lagre")
            }
        }
    }
}