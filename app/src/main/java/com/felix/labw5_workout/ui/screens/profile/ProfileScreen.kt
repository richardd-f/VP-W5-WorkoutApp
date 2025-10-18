package com.felix.labw5_workout.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.R
import com.felix.labw5_workout.data.DummyUsersData
import com.felix.labw5_workout.data.DummyWorkoutsData
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.model.WorkoutModel
import com.felix.labw5_workout.navigation.Screen
import com.felix.labw5_workout.ui.components.BottomNavigationBar
import com.felix.labw5_workout.ui.components.FriendSuggestionCard
import com.felix.labw5_workout.ui.components.WorkoutCard


@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    val loggedAccount by viewModel.loggedAccount.collectAsState()
    ProfileScreenContent(
        loggedAccount = loggedAccount!!,
        allUserExceptMe = viewModel.getAllUserExceptMe(),
        allMyFriends = viewModel.getAllMyFriends(),
        isFriend = {viewModel.isFriend(it)},
        onAddFriend = {viewModel.addFriend(it)},
        allWorkouts = viewModel.allWorkout,
        isWorkoutAdded = { viewModel.isWorkoutAdded(it) },
        onClickWorkoutBtn = { title, isAlreadyAdded -> viewModel.clickWorkoutBtn(title, isAlreadyAdded) },
        navController = navController
    )
}

@Composable
fun ProfileScreenContent(
    loggedAccount: UserModel,
    allUserExceptMe: List<UserModel>,
    allMyFriends: List<UserModel>,
    isFriend: (Int) -> Boolean,
    onAddFriend: (Int) ->  Unit,
    allWorkouts: List<WorkoutModel>,
    isWorkoutAdded: (String)->Boolean,
    onClickWorkoutBtn: (String, Boolean)->Unit,
    navController: NavController
){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentScreen = Screen.Profile.route)
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
            // Text "Profile"
            Text(
                modifier = Modifier
                    .padding(bottom = 18.dp),
                text = "Profile",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
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
                    // Nama
                    Text(
                        text = "${loggedAccount?.name}, ${loggedAccount?.age}",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.Black
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
                text = "Recently Added",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            if(allMyFriends.isNotEmpty()){
                LazyRow {
                    items(
                        items = allMyFriends,
                        key = { user -> "${user.id}-${loggedAccount?.friends?.size}" }
                    ) { user ->
                        val isAlreadyFriend =  isFriend(user.id)

                        FriendSuggestionCard(
                            user = user,
                            onAddFriendClick = { onAddFriend(user.id) },
                            isAlreadyFriend = isAlreadyFriend,
                            onRemoveFriendClick = {},
                            showButton = false
                        )
                        Spacer(Modifier.width(10.dp))
                    }
                }
            }else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Friends Yet",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
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
                color = Color.Black
            )
            LazyColumn {
                items(
                    items = allWorkouts,
                    key = { workout -> "${workout.title}-${loggedAccount?.workouts?.size}" }
                ){ workout ->
                    val isWorkoutAlreadyAdded = isWorkoutAdded(workout.title)
                    WorkoutCard(
                        workout = workout,
                        onButtonClick = {onClickWorkoutBtn(workout.title, isWorkoutAlreadyAdded)},
                        isAlreadyAdded = isWorkoutAlreadyAdded
                    )
                }
            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview(){
    ProfileScreenContent(
        loggedAccount = DummyUsersData().users[0],
        allUserExceptMe = DummyUsersData().users,
        allMyFriends = emptyList(),
        isFriend = {false},
        onAddFriend = {},
        allWorkouts = DummyWorkoutsData().workouts,
        isWorkoutAdded = { false },
        onClickWorkoutBtn = { _, _ -> },
        navController = NavController(LocalContext.current)
    )
}