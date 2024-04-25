package com.example.exercise5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.exercise5.Components.GameView
import com.example.exercise5.Components.StartView
import com.example.exercise5.ui.theme.Exercise5Theme

class MainActivity : ComponentActivity() {
    private val gameViewModel by viewModels<GameViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exercise5Theme {
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold {
                        Column {
                            when(gameViewModel.viewOpen){
                                AppView.START  ->{
                                    StartView(gameViewModel::startGame)
                                }
                                else ->{
                                    GameView(
                                        gameViewModel::guessNumber,
                                        gameViewModel.gameState,
                                        gameViewModel::openView
                                    )
                                }
                            }
                            if (gameViewModel.feedback != null) {
                                Text(gameViewModel.feedback!!)
                            }
                        }
                    }
                }
            }
        }
    }
}