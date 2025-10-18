package com.felix.labw5_workout.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.felix.labw5_workout.ui.screens.friends.FriendScreen
import com.felix.labw5_workout.ui.screens.profile.ProfileScreen
import com.felix.labw5_workout.ui.screens.workouts.WorkoutScreen

sealed class Screen(val route: String) {
    object Profile : Screen("profile")
    object Workouts : Screen("workouts")
    object Friends : Screen("friends")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Profile.route) {
        composable(Screen.Profile.route) { ProfileScreen( navController ) }
        composable(Screen.Friends.route) { FriendScreen(navController) }
        composable(Screen.Workouts.route) { WorkoutScreen(navController) }
    }
}