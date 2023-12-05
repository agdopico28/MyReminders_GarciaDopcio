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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyTopAppBar(navHostController: NavHostController, drawerState: DrawerState, scope: CoroutineScope, onMenuIconClick: () -> Unit){
    var isMenuVisible by remember { mutableStateOf(false) }
    TopAppBar(//barra de menu parte superior de la pantalla
        title = {
            Text(
                text = "Reminders",
                color = Color.Black,
                fontSize = 40.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontTittle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp)
            ) //nombre que aparece en la barra
        },

        navigationIcon = {
            IconButton(
                onClick = {
                    onMenuIconClick
                }
            ) {
                Icon(//Icono de las tres barras horizontales
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        },
        actions = {
            IconButton(
                onClick = {
                    navHostController.navigate("Hidden")
                }
            ) {
                Icon(//Icono de las tres barras horizontales
                    imageVector = Icons.Default.Lock,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            IconButton(
                onClick = {
                    navHostController.navigate("New Note")
                }
            ) {
                Icon(//Icono de las tres barras horizontales
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.Black
                )
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
                    fontSize = 40.sp,
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