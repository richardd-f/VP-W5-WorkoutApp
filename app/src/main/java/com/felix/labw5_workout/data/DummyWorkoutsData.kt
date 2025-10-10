package com.felix.labw5_workout.data

import com.felix.labw5_workout.ui.model.WorkoutModel

class DummyWorkoutsData {
    val workouts: List<WorkoutModel> = listOf(
        WorkoutModel("Morning Yoga", WorkoutModel.Category.Flexibility),
        WorkoutModel("Cardio Blast", WorkoutModel.Category.Cardio),
        WorkoutModel("Strength Training", WorkoutModel.Category.Strength),
        WorkoutModel("Evening Run", WorkoutModel.Category.LightCardio),
        WorkoutModel("Jogging Session", WorkoutModel.Category.Cardio),
        WorkoutModel("Interval Run", WorkoutModel.Category.Cardio),
        WorkoutModel("Cycling Endurance", WorkoutModel.Category.Cardio),
        WorkoutModel("Push-Up Challenge", WorkoutModel.Category.Strength),
        WorkoutModel("Dumbbell Lifts", WorkoutModel.Category.Strength),
        WorkoutModel("Full Body Workout", WorkoutModel.Category.Strength)
    )
}
