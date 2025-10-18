package com.felix.labw5_workout.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.labw5_workout.model.UserModel

@Composable
fun FriendSuggestionCard(
    user: UserModel,
    onAddFriendClick: () -> Unit,
    isAlreadyFriend: Boolean,
    modifier: Modifier = Modifier
) {
    val name: String = user.name
    val age: Int = user.age
    val shortName = name.split(" ").let {
        if (it.size > 1) {
            "${it[0]} ${it[1][0]}."
        } else {
            it[0]
        }
    }

    Card(
        modifier = modifier
            .width(180.dp)
            .padding(vertical = 8.dp)
            .padding(end = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE6F5FA)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Icon Placeholder
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile",
                tint = Color.DarkGray,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(
                        color = Color(0xFFAAE1F0),
                        shape = CircleShape
                    )
                    .width(60.dp)
                    .padding(10.dp)
                    .aspectRatio(1f)
            )

            // Name
            Text(
                text = shortName,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )

            // Age
            Text(
                text = "$age years old",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Add Friend Button
            Button(
                onClick = {onAddFriendClick()},
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF55B9E1),
                    disabledContainerColor = Color.Gray
                ),
                shape = RoundedCornerShape(50),
                enabled = !isAlreadyFriend
            ) {
                Text(
                    text = "Add Friend",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewFriendSuggestionCard(){
//    FriendSuggestionCard(DummyUsersData().users[0])
}
