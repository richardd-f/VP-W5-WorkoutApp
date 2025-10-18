package com.felix.labw5_workout.model

import com.felix.labw5_workout.R

class WorkoutModel (
    val title: String,
    val category: Category,
    val calories:Int
) {
    enum class Category (val text: String){
        Flexibility(text= "Flexibility"),
        Cardio(text = "Cardio"),
        Strength(text = "Strength"),
        LightCardio(text = "Light Cardio")
    }
    var imageRes: Int = when(category){
        Category.Flexibility -> R.drawable.stretching
        Category.Cardio -> R.drawable.run
        Category.Strength -> R.drawable.deadlift
        Category.LightCardio -> R.drawable.walk
    }
}