package com.felix.labw5_workout.ui.screens.workouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.navigation.Screen

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel:WorkoutViewModel = viewModel()
){
    WorkoutScreenContent()
}

@Composable
fun WorkoutScreenContent(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WorkoutScreenPreview(){
    WorkoutScreenContent()
}