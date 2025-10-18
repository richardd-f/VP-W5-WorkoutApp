package com.felix.labw5_workout.ui.screens.workouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.R
import com.felix.labw5_workout.data.DummyUsersData
import com.felix.labw5_workout.data.DummyWorkoutsData
import com.felix.labw5_workout.model.UserModel
import com.felix.labw5_workout.model.WorkoutModel
import com.felix.labw5_workout.navigation.Screen
import com.felix.labw5_workout.ui.components.BottomNavigationBar
import com.felix.labw5_workout.ui.components.FriendSuggestionCard
import com.felix.labw5_workout.ui.components.WorkoutCard
import com.felix.labw5_workout.ui.screens.profile.MainViewModel

@Composable
fun WorkoutScreen(
    navController: NavController,
    viewModel:MainViewModel = viewModel()
){
    WorkoutScreenContent(
        navController = navController,
        allWorkouts = viewModel.allWorkout.collectAsState().value,
        loggedAccount = viewModel.loggedAccount.collectAsState().value!!,
        isWorkoutAdded = { viewModel.isWorkoutAdded(it) },
        onClickWorkoutBtn = { title, isAlreadyAdded ->
            viewModel.clickWorkoutBtn(
                title,
                isAlreadyAdded
            )
        },
        showDialog = viewModel.showDialog.collectAsState().value,
        titleDialog = viewModel.titleDialog.collectAsState().value,
        typeDialog = viewModel.typeDialog.collectAsState().value,
        caloriesDialog = viewModel.caloriesDialog.collectAsState().value,
        iconDialog = viewModel.iconDialog.collectAsState().value,
        onShowDialogChange = { viewModel.setShowDialog(it) },
        onTitleDialogChange = { viewModel.setTitleDialog(it) },
        onTypeDialogChange = {viewModel.setTypeDialog(it)},
        onCaloriesDialogChange = {viewModel.setCaloriesDialog(it)},
        onIconDialogChange = {viewModel.setIconDialog(it)},
        icons = viewModel.icons,
        onClickSaveWorkout = {viewModel.newWorkout()}
    )
}

@Composable
fun WorkoutScreenContent(
    navController: NavController,
    allWorkouts: List<WorkoutModel>,
    loggedAccount: UserModel,
    isWorkoutAdded: (String)->Boolean,
    onClickWorkoutBtn: (String, Boolean)->Unit,

    // Dialog State (from ViewModel)
    showDialog: Boolean,
    titleDialog: String,
    typeDialog: String,
    caloriesDialog: Int?,
    iconDialog: Int?,

    // Dialog State Updaters (from ViewModel)
    onShowDialogChange: (Boolean) -> Unit,
    onTitleDialogChange: (String) -> Unit,
    onTypeDialogChange: (String) -> Unit,
    onCaloriesDialogChange: (Int?) -> Unit,
    onIconDialogChange: (Int?) -> Unit,

    onClickSaveWorkout: () -> Unit,
    icons: List<Int>

){
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController, currentScreen = Screen.Workouts.route)
        },
        floatingActionButton = {
            FloatingActionButton (
                onClick = { onShowDialogChange(true) },
                containerColor = Color(0xFF3782F5),
                shape = CircleShape,
                modifier = Modifier
                    .padding(bottom = 0.dp, end = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Workout",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        // Profile Information
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White)
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .padding(innerPadding)
        ) {
            // Text "Workout"
            Text(
                modifier = Modifier
                    .padding(bottom = 18.dp),
                text = "Workouts",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            // Workout List (using WorkoutCard)
            if(allWorkouts.isNotEmpty()){
                LazyColumn {
                    items(
                        items = allWorkouts,
                        key = { workout -> "${workout.title}-${loggedAccount?.workouts?.size}" }
                    ){ workout ->
                        val isWorkoutAlreadyAdded = isWorkoutAdded(workout.title)
                        WorkoutCard(
                            workout = workout,
                            onButtonClick = {onClickWorkoutBtn(workout.title, isWorkoutAlreadyAdded)},
                            isAlreadyAdded = isWorkoutAlreadyAdded
                        )
                    }
                }
            }else{
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Workouts Yet",
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    // ✅ ADD WORKOUT DIALOG
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { onShowDialogChange(false) },
            title = {
                Text("Add New Workout", fontWeight = FontWeight.Bold, color = Color.Black)
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = titleDialog,
                        onValueChange = { onTitleDialogChange(it) },
                        label = { Text("Workout Title") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    OutlinedTextField(
                        value = typeDialog,
                        onValueChange = { onTypeDialogChange(it) },
                        label = { Text("Workout Type") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    OutlinedTextField(
                        value = caloriesDialog?.toString() ?: "",
                        onValueChange = {
                            val intValue = it.toIntOrNull()
                            onCaloriesDialogChange(intValue)
                        },
                        label = { Text("Calories Burned") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                        )
                    )

                    // Icon selection row
                    Text("Choose Icon:")
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        icons.forEach { icon ->
                            val isSelected = icon == iconDialog

                            IconButton(
                                onClick = { onIconDialogChange(icon) },
                                modifier = Modifier
                                    .background(
                                        color = if (isSelected) Color(0xFFBBDEFB) else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .size(60.dp)
                            ) {
                                Image(
                                    painter = painterResource(icon),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(40.dp)
                                )
                            }
                        }
                    }

                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onClickSaveWorkout()
                        onShowDialogChange(false)
                    }
                ) {
                    Text("Save Workout", color = Color(0xFF3782F5))
                }
            },
            dismissButton = {
                TextButton(onClick = { onShowDialogChange(false) }) {
                    Text("Cancel", color = Color.Gray)
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WorkoutScreenPreview(){
    WorkoutScreenContent(
        navController = NavController(LocalContext.current),
        allWorkouts = DummyWorkoutsData().workouts,
        loggedAccount = DummyUsersData().users[0],
        isWorkoutAdded = { false },
        onClickWorkoutBtn = { _, _ -> },
        showDialog = false,
        titleDialog = "Running",
        typeDialog = "Cardio",
        caloriesDialog = 200,
        iconDialog = R.drawable.run,
        onShowDialogChange = {},
        onTitleDialogChange = {},
        onTypeDialogChange = {},
        onCaloriesDialogChange = {},
        onIconDialogChange = {},
        icons = emptyList(),
        onClickSaveWorkout = {}
    )
}