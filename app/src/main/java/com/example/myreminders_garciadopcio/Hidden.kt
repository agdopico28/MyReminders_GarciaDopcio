package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun hidden(navHostController: NavHostController){
    Scaffold (
        topBar = { TopAppBarNew(navHostController = navHostController) }
    ){
        Column (
            Modifier.padding(top = it.calculateTopPadding())
        ){
           // PantallaPrincipal()
        }

    }

}