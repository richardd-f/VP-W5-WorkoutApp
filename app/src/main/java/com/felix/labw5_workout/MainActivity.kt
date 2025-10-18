package com.felix.labw5_workout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.felix.labw5_workout.navigation.AppNavigation
import com.felix.labw5_workout.ui.theme.LabW5_WorkoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabW5_WorkoutTheme {
                AppNavigation()
            }
        }
    }
}