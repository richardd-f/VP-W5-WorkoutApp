package com.felix.labw5_workout.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.labw5_workout.model.WorkoutModel

@Composable
fun WorkoutCard(
    workout: WorkoutModel,
    isAlreadyAdded: Boolean,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    showButton:Boolean = true
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE8F5E9)
        ),
    ) {
        Row (
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Icon (placeholder)
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .background(Color(0xFFA5D6A7), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(workout.imageRes),
                    contentDescription = "Workout icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            // Title & Category
            Column (
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
            ) {
                Text(
                    text = workout.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
                Text(
                    text = "${workout.calories} Cals",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Black
                    )
                )
                Text(
                    text = workout.category.text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.Gray
                    )
                )
            }

            // Add Button
            if(showButton){
                IconButton(
                    onClick = onButtonClick,
                    modifier = Modifier
                        .weight(0.15f)
                        .aspectRatio(1f)
                        .background(
                            color = if (!isAlreadyAdded) Color(0xFF42A5F5)
                            else Color.Red,
                            shape = CircleShape
                        ),
                ) {
                    Icon(
                        imageVector = if(!isAlreadyAdded) Icons.Default.Add
                            else Icons.Default.Remove,
                        contentDescription = "Add workout",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun PreviewWorkoutCard(){
//    WorkoutCard(
//        title = "Workout Title",
//        category = "Category",
//        imageRes = R.drawable.run,
////        onAddClick = {}
//    )
}