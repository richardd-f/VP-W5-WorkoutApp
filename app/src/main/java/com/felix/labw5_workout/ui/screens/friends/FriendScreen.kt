package com.felix.labw5_workout.ui.screens.friends

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.felix.labw5_workout.ui.screens.profile.MainViewModel

@Composable
fun FriendScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
){
    FriendScreenContent()
}

@Composable
fun FriendScreenContent(){

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FriendScreenPreview(){
    FriendScreenContent()
}