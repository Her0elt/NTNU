package com.example.exercise5.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun StartView(startGame: (String, String) -> Unit,){
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val cardNumberState = remember { mutableStateOf(TextFieldValue()) }

    fun start() {
        startGame(nameState.value.text, cardNumberState.value.text)
    }
    Column {
        Text("Fyll ut ditt navn og kortnummer for å starte spillet")
        TextField(
            value = nameState.value,
            onValueChange = { nameState.value = it },
            label = { Text("Navn") })
        TextField(
            value = cardNumberState.value,
            onValueChange = { cardNumberState.value = it },
            label = { Text("Kortnummer") }
        )
        Button(onClick = { start() }) {
            Text("Start spill")
        }
    }
}