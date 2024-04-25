package com.example.exercise3.friends

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
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
internal fun FriendsList(
    navController: NavController,
    friends: List<Friend>,
    addFriend: (String, String) -> Unit
) {
    Column {
        LazyColumn(contentPadding = PaddingValues(12.dp)) {
            item {
                Text(text = "Legg til venn")
                AddFriendComponent(addFriend)
                Divider()
            }
            items(friends) { friend ->
                FriendListItem(navController, friend)
            }
        }
    }
}

@Composable
private fun FriendListItem(navController: NavController, friend: Friend) {
    Column(
        Modifier
            .clickable { navController.navigate("friends/${friend.id}") }
            .padding(8.dp)) {
        Text(text = "Navn: ${friend.name}")
        Text(text = "Fødselsdag: ${friend.birthDate}")
        Divider()
    }
}

@Composable
private fun AddFriendComponent(
    addFriend: (String, String) -> Unit
) {
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val birthdateState = remember { mutableStateOf(TextFieldValue()) }
    fun add() {
        addFriend(nameState.value.text, birthdateState.value.text)
    }
    Column {
        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Navn") })
        TextField(
            value = birthdateState.value,
            onValueChange = { birthdateState.value = it },
            label = { Text("Fødselsdag") }
        )
        Button(onClick = { add() }) {
            Text("Legg til venn")
        }
    }
}

