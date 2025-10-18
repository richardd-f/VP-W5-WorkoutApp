package com.felix.labw5_workout.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.R
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.ui.components.FriendSuggestionCard
import com.felix.labw5_workout.ui.components.WorkoutCard


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel()
){
    val loggedAccount by viewModel.loggedAccount.collectAsState()
    ProfileScreenContent(
        loggedAccount = loggedAccount!!,
        allUserExceptMe = viewModel.getAllUserExceptMe(),
        isFriend = {viewModel.isFriend(it)}
    )
}

@Composable
fun ProfileScreenContent(
    loggedAccount: UserModel,
    allUserExceptMe: List<UserModel>,
    isFriend: (Int) -> Boolean
){
    // Profile Information
    Column (
        modifier = Modifier
            .padding(top = 50.dp)
            .padding(horizontal = 20.dp)
    ) {
        // Text "Profile"
        Text(
            modifier = Modifier
                .padding(bottom = 18.dp),
            text = "Profile",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
        )

        // Profile Information Card
        Row (
            modifier = Modifier
                .background(
                    color = Color(0xFFE6F0FA),
                    shape = RoundedCornerShape(15.dp)
                )
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            // Profile Image Logo
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Profile",
                Modifier
                    .background(
                        color = Color(0xFFA5CDF5),
                        shape = CircleShape
                    )
                    .width(80.dp)
                    .aspectRatio(1f)
                    .padding(14.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))

            // Profile Description
            Column {
                Text(
                    text = "${loggedAccount?.name}, ${loggedAccount?.age}",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "${loggedAccount?.height} cm / ${loggedAccount?.weight} kg",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Calories Burned
                    Image(
                        modifier = Modifier
                            .padding(end = 3.dp)
                            .width(15.dp),
                        painter = painterResource(R.drawable.fire),
                        contentDescription = "Fire Calories",
                    )
                    Text(
                        text = "${loggedAccount?.workouts?.size}",
                        color = Color(0xFFFF7B00),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.width(10.dp))

                    // Friends Amount
                    Icon(
                        modifier = Modifier
                            .padding(end = 3.dp)
                            .width(15.dp),
                        imageVector = Icons.Filled.People,
                        contentDescription = "Friends Amount Logo",
                        tint = Color(0xFF3782F5)
                    )
                    Text(
                        text = "${loggedAccount?.friends?.size}",
                        color = Color(0xFF3782F5),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }

        // Friend Suggestion (using FriendSuggestionCard)
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = "Friend Suggestion",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyRow {
            items(
                items = allUserExceptMe,
                key = { user -> "${user.id}-${loggedAccount?.friends?.size}" }
            ) { user ->
                val isAlreadyFriend = { loggedAccount.friends.any{ it.id == user.id } }

                FriendSuggestionCard(
                    user = user,
                    onAddFriendClick = { viewModel.addFriend(user.id) },
                    isAlreadyFriend = isAlreadyFriend
                )
            }
        }

        // Workout List (using WorkoutCard)
        Text(
            modifier = Modifier
                .padding(vertical = 10.dp),
            text = "Workout List",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyColumn {
            items(
                items = viewModel.allWorkout,
                key = { workout -> "${workout.title}-${loggedAccount?.workouts?.size}" }
            ){ workout ->
                val isWorkoutAlreadyAdded = viewModel.isWorkoutAdded(workout.title)
                WorkoutCard(
                    workout = workout,
                    onButtonClick = {viewModel.clickWorkoutBtn(workout.title, isWorkoutAlreadyAdded)},
                    isAlreadyAdded = isWorkoutAlreadyAdded
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreenContent()
}