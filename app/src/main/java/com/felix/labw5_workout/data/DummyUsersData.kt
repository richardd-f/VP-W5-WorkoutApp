package com.felix.labw5_workout.data

import com.felix.labw5_workout.model.UserModel
import java.time.LocalDate

class DummyUsersData {
    val users: List<UserModel> = listOf(
        UserModel(
            "Jamier Tanuwijaya",
            birthdate = LocalDate.of(1999, 3, 21),
            height = 166,
            weight = 80
        ),
        UserModel(
            "Felix Richardo",
            birthdate = LocalDate.of(2006, 6, 30),
            height = 172,
            weight = 68
        ),
        UserModel(
            "Jessica Limantara",
            birthdate = LocalDate.of(1999, 11, 5),
            height = 160,
            weight = 54
        ),
        UserModel(
            "Darren Kusuma",
            birthdate = LocalDate.of(1998, 2, 14),
            height = 178,
            weight = 75
        ),
        UserModel(
            "Elena Wijaya",
            birthdate = LocalDate.of(2001, 6, 3),
            height = 158,
            weight = 52
        ),
        UserModel(
            "Samuel Prasetyo",
            birthdate = LocalDate.of(1997, 9, 30),
            height = 181,
            weight = 79
        ),
        UserModel(
            "Michelle Tan",
            birthdate = LocalDate.of(2000, 12, 22),
            height = 164,
            weight = 56
        ),
        UserModel(
            "Rayhan Aditya",
            birthdate = LocalDate.of(2003, 8, 19),
            height = 175,
            weight = 70
        ),
        UserModel(
            "Cindy Halim",
            birthdate = LocalDate.of(1996, 5, 9),
            height = 162,
            weight = 58
        ),
        UserModel(
            "Bryan Santoso",
            birthdate = LocalDate.of(1995, 1, 27),
            height = 180,
            weight = 77
        ),
        UserModel(
            "Vivian Lee",
            birthdate = LocalDate.of(2004, 10, 15),
            height = 167,
            weight = 60
        ),
        UserModel(
            "Nicholas Chandra",
            birthdate = LocalDate.of(1999, 4, 11),
            height = 173,
            weight = 74
        )
    )
}
