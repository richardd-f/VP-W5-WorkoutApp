package com.felix.labw5_workout.ui.screens.workouts

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.R
import com.felix.labw5_workout.data.DummyWorkoutsData
import com.felix.labw5_workout.model.WorkoutModel
import com.felix.labw5_workout.navigation.Screen
import com.felix.labw5_workout.ui.components.BottomNavigationBar
import com.felix.labw5_workout.ui.components.FriendSuggestionCard
import com.felix.labw5_workout.ui.components.WorkoutCard

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel:WorkoutViewModel = viewModel()
){
    WorkoutScreenContent(
        navController = navController,
        allWorkouts = viewModel.
    )
}

@Composable
fun WorkoutScreenContent(
    navController: NavController,
    allWorkouts: List<WorkoutModel>,
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

            // Workout List (using WorkoutCard)
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
fun WorkoutScreenPreview(){
    WorkoutScreenContent()
}