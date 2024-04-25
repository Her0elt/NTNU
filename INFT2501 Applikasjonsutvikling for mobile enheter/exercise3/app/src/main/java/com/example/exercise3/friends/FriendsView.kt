package com.example.exercise3

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.exercise3.friends.Friend
import com.example.exercise3.friends.FriendDetails
import com.example.exercise3.friends.FriendsList

@Composable
internal fun FriendView(
    friends: List<Friend>,
    addFriend: (String, String) -> Unit,
    editFriend: (Int, String, String) -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "friends") {
        composable(route = "friends") {
            FriendsList(
                navController, friends, addFriend
            )
        }
        composable(
            route = "friends/{friendId}",
            arguments = listOf(navArgument("friendId") { type = NavType.IntType })
        ) {
            FriendDetails(
                navController,
                it.arguments!!.getInt("friendId"),
                friends,
                editFriend
            )
        }
    }
}


