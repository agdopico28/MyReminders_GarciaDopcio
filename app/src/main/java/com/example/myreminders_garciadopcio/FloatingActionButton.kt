package com.example.myreminders_garciadopcio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color2

@Composable
fun MyFLoadinActionButtonAdd(navHostController: NavHostController){
    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(
            //When you click the button, the application navigates to the "Reminders_new" screen
            onClick = {
                navHostController.navigate("Reminders_new")
            },
            //Change the color of the FAB
            containerColor = Color2,
            //We modify the size of the button and the size of the icon that goes inside it
            modifier = Modifier
                .padding(horizontal = 15.dp, vertical = 15.dp)
                .height(50.dp)
                .width(50.dp))
        {
            //Specifications of the icon that goes inside the FAB
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "New Reminders",
                tint = Color.Black,
                modifier = Modifier.size(30.dp)
                )
        }

    }
}
