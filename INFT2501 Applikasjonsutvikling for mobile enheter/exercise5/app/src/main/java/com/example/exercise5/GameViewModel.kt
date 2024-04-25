package com.example.exercise5

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.exercise5.Components.HTTPHandler




class GameViewModel : ViewModel() {

    private val BASE_URL = "https://bigdata.idi.ntnu.no/mobil/tallspill.jsp"
    private val network: HTTPHandler = HTTPHandler(BASE_URL)

    var viewOpen by mutableStateOf(AppView.START)
    var gameState by mutableStateOf(GameState.BEFORE_FIRST_GUESS)
        private set

    var feedback: String? = null
        private set

    private fun showFeedback(message: String) {
        Log.i("Snackbar", "Opening snackbar with message: $message")
        feedback = "Tilbakemelding:\n$message"
    }

    fun openView(view: AppView) {
        if (view == AppView.GAME) {
            gameState = GameState.BEFORE_FIRST_GUESS
        }
        viewOpen = view
        feedback = null
    }

    fun startGame(name: String, cardNumber: String) {
        Log.i("StartGame", "Starting game by sending request")
        performRequest(network, mapOf("navn" to name, "kortnummer" to cardNumber)) {
            Log.i("StartGame", "Received response to startGame request: $it")
            val startSuccessMsg = "Oppgi et"
            if (it.contains(startSuccessMsg, ignoreCase = true)) {
                Log.i("StartGame", "StartGame request succeeded")
                openView(AppView.GAME)
            }
            showFeedback(it)
        }
    }

    fun guessNumber(number: String) {
        Log.i("GuessNumber", "Guessing number by sending request")
        performRequest(network, mapOf("tall" to number)) {
            Log.i("GuessNumber", "Received response to GuessNumber request: $it")
            val wonSuccessMsg = "du har vunnet 100 kr som kommer inn på ditt kort"
            showFeedback(it)
            if (it.contains(wonSuccessMsg, ignoreCase = true)) {
                gameState = GameState.CORRECT_GUESS
                Log.i("GuessNumber", "Correct guess")
            } else {
                when (gameState) {
                    GameState.BEFORE_FIRST_GUESS -> gameState = GameState.BEFORE_SECOND_GUESS
                    GameState.BEFORE_SECOND_GUESS -> gameState = GameState.BEFORE_THIRD_GUESS
                    GameState.BEFORE_THIRD_GUESS -> gameState = GameState.NO_MORE_GUESSES
                    else -> Log.e("Gamestate", "Gamestate is in an unknown state")
                }
            }
        }
    }
}

