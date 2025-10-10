package com.felix.labw5_workout

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.felix.labw5_workout.ui.theme.LabW5_WorkoutTheme
import com.felix.labw5_workout.ui.viewModel.HomePageViewModel
import com.felix.labw5_workout.ui.viewModel.HomePageViewModelFactory
import com.felix.labw5_workout.ui.views.Homepage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabW5_WorkoutTheme {
                val factory = HomePageViewModelFactory(1)
                val viewModel:HomePageViewModel = viewModel(factory = factory)
                Homepage(viewModel)
            }
        }
    }
}