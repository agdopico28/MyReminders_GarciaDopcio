package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color1
import com.example.myreminders_garciadopcio.ui.theme.Color2
import com.example.myreminders_garciadopcio.ui.theme.FontTittle


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyTopAppBar(navHostController: NavHostController){
    var isMenuVisible by remember { mutableStateOf(false) }
    TopAppBar(//barra de menu parte superior de la pantalla
        title = {
            Text(
                text = "Reminders",
                color = Color.Black,
                fontSize = 35.sp,
                fontFamily = FontTittle
            ) //nombre que aparece en la barra
        },
        navigationIcon = {
            IconButton(
                onClick = {
                }
            ) {
                Icon(//Icono de las tres barras horizontales
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
//            Image(
//                painter = painterResource(id = R.drawable.logo_reminders),
//                contentDescription = null,
//                modifier = Modifier.size(10.dp)
//            )
        },
        actions = {
            Row (){
                IconButton( //Icono de los tres puntos que cuando clicas aparece el dropdownMenu
                    onClick = {
                        isMenuVisible = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }
            Row (){
                DropdownMenu(
                    expanded = isMenuVisible,
                    onDismissRequest = {
                        isMenuVisible = false
                    },
                    modifier = Modifier.background(Color2)
                ) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Ocultos",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = {navHostController.navigate("Ocultos") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = null,
                                tint = Color.Black
                            ) },
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color1)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TopAppBarNew(navHostController: NavHostController){
    TopAppBar(
        title = {
                Text(text = "New Note",
                    color = Color.Black,
                    fontSize = 35.sp,
                    fontFamily = FontTittle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 50.dp)
                )

        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navHostController.navigate("Reminders_show")
                }
            ) {
                Icon(//Icono de las tres barras horizontales
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color1),
    )
}