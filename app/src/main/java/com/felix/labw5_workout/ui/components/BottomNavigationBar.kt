package com.felix.labw5_workout.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.felix.labw5_workout.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController, currentScreen:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE6F0FA))
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavButton(
            icon = Icons.Filled.Person,
            label = "Profile",
            onClickBtn = {
                if (currentScreen != Screen.Profile.route) {
                    navController.navigate(Screen.Profile.route)
                }
            }
        )
        BottomNavButton(
            icon = Icons.Filled.FitnessCenter,
            label = "Workouts",
            onClickBtn = {
                if (currentScreen != Screen.Workouts.route) {
                    navController.navigate(Screen.Workouts.route)
                }
            },
        )
        BottomNavButton(
            icon = Icons.Filled.People,
            label = "Friends",
            onClickBtn = {
                if (currentScreen != Screen.Friends.route) {
                    navController.navigate(Screen.Friends.route)
                }
            }
        )
    }
}

@Composable
fun BottomNavButton(
    icon: ImageVector,
    label: String,
    onClickBtn:()-> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClickBtn() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color(0xFF3782F5),
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF3782F5)
        )
    }
}
