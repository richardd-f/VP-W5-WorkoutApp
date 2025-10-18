package com.felix.labw5_workout.ui.screens.profile

import androidx.compose.material.icons.Icons
import androidx.lifecycle.ViewModel
import com.felix.labw5_workout.R
import com.felix.labw5_workout.data.DummyUsersData
import com.felix.labw5_workout.data.DummyWorkoutsData
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.model.WorkoutModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel() : ViewModel() {
    val userId: Int = 1
    val allUser: List<UserModel> = DummyUsersData().users
//    val allWorkout: List<WorkoutModel> = DummyWorkoutsData().workouts
    val icons = listOf(
        R.drawable.run,
        R.drawable.stretching,
        R.drawable.walk,
        R.drawable.deadlift
    )

    // Prvate mutable
    val _allWorkout = MutableStateFlow<List<WorkoutModel>>(DummyWorkoutsData().workouts)
    private val _loggedAccount = MutableStateFlow<UserModel?>(null)
    private val _showDialog = MutableStateFlow<Boolean>(false)
    private val _titleDialog  = MutableStateFlow<String>("")
    private val _typeDialog = MutableStateFlow<String>("")
    private val _caloriesDialog = MutableStateFlow<Int?>(null)
    private val _iconDialog = MutableStateFlow<Int?>(null)


    // public immutable
    val allWorkout: StateFlow<List<WorkoutModel>> = _allWorkout.asStateFlow()
    val loggedAccount: StateFlow<UserModel?> = _loggedAccount.asStateFlow()
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()
    val titleDialog: StateFlow<String> = _titleDialog.asStateFlow()
    val typeDialog: StateFlow<String> = _typeDialog.asStateFlow()
    val caloriesDialog: StateFlow<Int?> = _caloriesDialog.asStateFlow()
    val iconDialog: StateFlow<Int?> = _iconDialog.asStateFlow()


    fun setShowDialog(show: Boolean) {
        _showDialog.value = show
    }
    fun setTitleDialog(title: String) {
        _titleDialog.value = title
    }
    fun setTypeDialog(type: String) {
        _typeDialog.value = type
    }
    fun setCaloriesDialog(calories: Int?) {
        _caloriesDialog.value = calories
    }
    fun setIconDialog(iconResId: Int?) {
        _iconDialog.value = iconResId
    }

    init {
        _loggedAccount.value = allUser.find { it.id == userId }
    }

    // Workouts
    fun newWorkout() {
        val title = _titleDialog.value.trim()
        val type = _typeDialog.value.trim()
        val calories = _caloriesDialog.value ?: 0

        if (title.isEmpty() || type.isEmpty()) return

        // Convert type string into Category enum (default to Cardio if unknown)
        val category = when (type.lowercase()) {
            "flexibility" -> WorkoutModel.Category.Flexibility
            "cardio" -> WorkoutModel.Category.Cardio
            "strength" -> WorkoutModel.Category.Strength
            "light cardio" -> WorkoutModel.Category.LightCardio
            else -> WorkoutModel.Category.Cardio
        }
    }

    // FRIENDS
    fun getAllUserExceptMe(): List<UserModel> {
        return allUser.filter { it.id != _loggedAccount.value?.id }
    }
    fun getAllMyFriends(): List<UserModel> {
        return _loggedAccount.value?.friends ?: emptyList()
    }
    fun getAllWorkouts(): List<WorkoutModel> {
        return _loggedAccount.value?.workouts ?: emptyList()
    }

    fun addFriend(friendId: Int) {
        val friend = allUser.find { it.id == friendId } ?: return

        _loggedAccount.update { currentAccount ->
            currentAccount?.copy(
                friends = (currentAccount.friends + friend).distinctBy { it.id }
            )
        }
    }

    fun removeFriend(friendId: Int){
        val friend = allUser.find { it.id == friendId } ?: return
        _loggedAccount.update { currentAccount ->
            currentAccount?.copy(
                friends = currentAccount.friends - friend
            )
        }
    }

    fun isFriend(friendId: Int): Boolean {
        return _loggedAccount.value?.friends?.any { it.id == friendId } ?: false
    }

    // CALORIES
    fun addWorkout(workoutTitle: String) {
        val workout = _allWorkout.value.find { it.title == workoutTitle } ?: return
        _loggedAccount.update{ currentAccount ->
            currentAccount?.copy(
                workouts = currentAccount.workouts + workout
            )
        }
    }

    fun removeWorkout(workoutTitle: String) {
        val workout = _allWorkout.value.find { it.title == workoutTitle } ?: return
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