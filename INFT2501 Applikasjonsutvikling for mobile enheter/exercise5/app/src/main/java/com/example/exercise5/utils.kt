package com.example.exercise5

import android.util.Log
import com.example.exercise5.Components.HTTPHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun performRequest(
    network: HTTPHandler,
    parameterList: Map<String, String>,
    onResult: (String) -> Unit
) {
    CoroutineScope(IO).launch {
        val response: String = try {
            network.get(parameterList)
        } catch (e: Exception) {
            Log.e("performRequest()", e.message!!)
            e.printStackTrace()
            e.toString()
        }
        MainScope().launch {
            Log.e("Response", response)
            onResult(response)
        }
    }
}


enum class AppView {
    START,
    GAME,
}

enum class GameState {
    BEFORE_FIRST_GUESS,
    BEFORE_SECOND_GUESS,
    BEFORE_THIRD_GUESS,
    CORRECT_GUESS,
    NO_MORE_GUESSES,
}