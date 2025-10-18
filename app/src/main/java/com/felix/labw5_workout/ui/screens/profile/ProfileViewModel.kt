package com.felix.labw5_workout.ui.screens.profile

import androidx.lifecycle.ViewModel
import com.felix.labw5_workout.data.DummyUsersData
import com.felix.labw5_workout.data.DummyWorkoutsData
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.model.WorkoutModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel() : ViewModel() {
    val userId: Int = 1
    val allUser: List<UserModel> = DummyUsersData().users
    val allWorkout: List<WorkoutModel> = DummyWorkoutsData().workouts

    private val _loggedAccount = MutableStateFlow<UserModel?>(null)
    val loggedAccount: StateFlow<UserModel?> = _loggedAccount.asStateFlow()

    init {
        _loggedAccount.value = allUser.find { it.id == userId }
    }

    // FRIENDS
    fun getAllUserExceptMe(): List<UserModel> {
        return allUser.filter { it.id != _loggedAccount.value?.id }
    }

    fun addFriend(friendId: Int) {
        val friend = allUser.find { it.id == friendId } ?: return

        _loggedAccount.update { currentAccount ->
            currentAccount?.copy(
                friends = (currentAccount.friends + friend).distinctBy { it.id }
            )
        }
    }

    fun isFriend(friendId: Int): Boolean {
        return _loggedAccount.value?.friends?.any { it.id == friendId } ?: false
    }

    // CALORIES
    fun addWorkout(workoutTitle: String) {
        val workout = allWorkout.find { it.title == workoutTitle } ?: return
        _loggedAccount.update{ currentAccount ->
            currentAccount?.copy(
                workouts = currentAccount.workouts + workout
            )
        }
    }

    fun removeWorkout(workoutTitle: String) {
        val workout = allWorkout.find { it.title == workoutTitle } ?: return
        _loggedAccount.update{ currentAccount ->
            currentAccount?.copy(
                workouts = currentAccount.workouts - workout
            )
        }
    }

    fun clickWorkoutBtn(workoutTitle: String, isAlreadyAdded: Boolean) {
        if (isAlreadyAdded) {
            removeWorkout(workoutTitle)
        } else {
            addWorkout(workoutTitle)
        }
    }

    fun isWorkoutAdded(workoutTitle: String): Boolean {
        return _loggedAccount.value?.workouts?.any { it.title == workoutTitle } ?: false
    }

}