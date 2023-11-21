package com.example.myreminders_garciadopcio

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
            onClick = {
                navHostController.navigate("Reminders_new")
            },
            containerColor = Color2,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .height(50.dp)
                .width(50.dp))
        {

            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "New Reminders",
                tint = Color.Black,
                )
        }

    }
}

@Composable
fun MyFLoadinActionButtonBack(navHostController: NavHostController){
    Box (Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd){
        FloatingActionButton(
            onClick = {
                navHostController.navigate("Reminders_create")
            },
            containerColor = Color2,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .height(50.dp)
                .width(50.dp))
        {

            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Come back Reminders",
                tint = Color.Black,
            )
        }

    }
}