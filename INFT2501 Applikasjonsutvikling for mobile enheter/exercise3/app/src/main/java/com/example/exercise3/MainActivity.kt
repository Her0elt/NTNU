package com.example.exercise3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.exercise3.friends.FriendState
import com.example.exercise3.ui.theme.Exercise3Theme

class MainActivity : ComponentActivity() {
    private val friendsState by viewModels<FriendState>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Exercise3Theme {
                Surface(color = MaterialTheme.colors.background) {
                    FriendView(
                        friendsState.friends,
                        friendsState::addItem,
                        friendsState::updateFriend
                    )
                }
            }
        }
    }

}
