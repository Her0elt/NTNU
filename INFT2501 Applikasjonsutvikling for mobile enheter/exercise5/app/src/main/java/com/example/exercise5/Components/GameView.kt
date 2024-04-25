package com.example.exercise5.Components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.example.exercise5.AppView
import com.example.exercise5.GameState

@Composable
fun GameView(
    guessNumber: (String) -> Unit,
    gameState: GameState,
    setViewOpen: (AppView) -> Unit,
) {
    val guessState = remember { mutableStateOf(TextFieldValue()) }
    fun guess() {
        guessNumber(guessState.value.text)
    }
    Column {
        when (gameState) {
            GameState.BEFORE_FIRST_GUESS, GameState.BEFORE_SECOND_GUESS, GameState.BEFORE_THIRD_GUESS -> {
                Text("Skriv inn det tallet du tror er riktig")
                TextField(
                    value = guessState.value,
                    onValueChange = { guessState.value = it },
                    label = { Text("Gjett tall") })
                Button(onClick = { guess() }) {
                    Text("Gjett tall")
                }
            }
            GameState.NO_MORE_GUESSES -> {
                Text("Du har brukt opp dine forsøk på å gjette riktig, start et nytt spill for å prøve på nytt")
                Button(onClick = { setViewOpen(AppView.START) }) {
                    Text("Start nytt spill")
                }
            }
            GameState.CORRECT_GUESS -> {
                Text("Du har gjettet riktig! Gratulerer!")
                Button(onClick = { setViewOpen(AppView.START) }) {
                    Text("Start nytt spill")
                }
            }
        }
    }
}