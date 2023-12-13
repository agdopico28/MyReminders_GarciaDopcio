package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color1
import com.example.myreminders_garciadopcio.ui.theme.Color4
import com.example.myreminders_garciadopcio.ui.theme.FontTittle



@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun reminders(navHostController: NavHostController, onMenuIconClick: () -> Unit, viewModel: NotesViewModel){
    Scaffold (
        topBar = {
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
                            onMenuIconClick()
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
    ){ padding ->
        Column (Modifier.padding(padding)
        ){
            PantallaPrincipal(viewModel)
        }

        // MyModalNavigationDrawer(navHostController)
        //MyFLoadinActionButtonAdd(navHostController = navHostController)
    }

}

@Composable
fun PantallaPrincipal(viewModel: NotesViewModel) {


    LazyColumn() {
        items(viewModel.notesList) { note ->
            Card(
                colors = CardDefaults.cardColors(Color4),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column (modifier = Modifier.weight(0.8f) ){
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Title: ")
                                }
                                append(note.title)
                            },
                            fontSize = 18.sp,
                            modifier = Modifier.padding(
                                start = 10.dp, end = 10.dp,
                                bottom = 5.dp
                            )
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append("Description:\n\t")
                                }
                                append(note.description)
                            },
                            fontSize = 18.sp,
                            modifier = Modifier.padding(
                                start = 10.dp, end = 10.dp,
                                bottom = 5.dp
                            )
                        )
                    }

//                    Column(
//                        modifier = Modifier
//                            .weight(0.1f)
//                            .background(Color.Gray)
//                    ){}


                    Column (modifier = Modifier
                        .weight(0.1f)
                        .padding(5.dp)
                    ){
                        IconButton(
                            onClick = {
                                viewModel.deleteNote(note)
                                viewModel.addNoteToLocalList()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = null,

                            )
                        }
                        IconButton(onClick = {/*viewModel.deleteNote(note)*/}) {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = null,

                                )
                        }
                    }

                }


            }
        }
    }
}