package com.felix.labw5_workout.ui.screens.friends

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.data.DummyUsersData
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.navigation.Screen
import com.felix.labw5_workout.ui.components.BottomNavigationBar
import com.felix.labw5_workout.ui.components.FriendSuggestionCard
import com.felix.labw5_workout.ui.screens.profile.MainViewModel

@Composable
fun FriendScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    val loggedAccount by viewModel.loggedAccount.collectAsState()
    FriendScreenContent(
        navController = navController,
        allUserExceptMe = viewModel.getAllUserExceptMe(),
        loggedAccount = loggedAccount!!,
        isFriend = {viewModel.isFriend(it)},
        onAddFriend = {viewModel.addFriend(it)},
        onRemoveFriend = {viewModel.removeFriend(it)}
    )
}

@Composable
fun FriendScreenContent(
    navController: NavController,
    allUserExceptMe: List<UserModel>,
    loggedAccount: UserModel,
    isFriend: (Int) -> Boolean,
    onAddFriend: (Int) ->  Unit,
    onRemoveFriend: (Int) -> Unit

){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentScreen = Screen.Friends.route)
        }
    ) { innerPadding ->
        // Profile Information
        Column (
            modifier = Modifier
                .background(Color.White)
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
        ) {
            // Text "Friends"
            Text(
                modifier = Modifier
                    .padding(bottom = 18.dp),
                text = "Friends",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Friend Lazy Column (using FriendSuggestionCard)
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = allUserExceptMe.chunked(2),
                    key = { chunk -> chunk.joinToString { "${it.id}" } }
                ) { userChunk ->
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                    ) {
                        userChunk.forEach { user ->
                            val isAlreadyFriend = isFriend(user.id)

                            FriendSuggestionCard(
                                user = user,
                                onAddFriendClick = { onAddFriend(user.id) },
                                onRemoveFriendClick = {onRemoveFriend(user.id)},
                                isAlreadyFriend = isAlreadyFriend,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (userChunk.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }

        }

    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendScreenPreview(){
    FriendScreenContent(
        navController = NavController(LocalContext.current),
        allUserExceptMe = DummyUsersData().users,
        loggedAccount = DummyUsersData().users[0],
        isFriend = {false},
        onAddFriend = {},
        onRemoveFriend = {}
    )
}