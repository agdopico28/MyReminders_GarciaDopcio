package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import android.provider.CalendarContract.Reminders
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun reminders(navHostController: NavHostController){
    Scaffold (
        topBar = { MyTopAppBar(navHostController = navHostController) }
    ){
        Text(text = "hola", modifier = Modifier.padding(it.calculateTopPadding()))
        MyFLoadinActionButtonAdd(navHostController = navHostController)
    }

}