package com.felix.labw5_workout.ui.model

import java.time.LocalDate
import java.time.Period

data class UserModel(
    val name: String,
    val birthdate: LocalDate,
    val height: Int,
    val weight: Int,
    val id: Int = generateId(),
    val friends: List<UserModel> = listOf(),
    val workouts: List<WorkoutModel> = listOf()
) {
    companion object {
        private var nextId = 1
        private fun generateId(): Int = nextId++
    }
    val age: Int get() = Period.between(birthdate, LocalDate.now()).years
}