package com.felix.labw5_workout.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomePageViewModelFactory(private val userId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomePageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomePageViewModel(userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}