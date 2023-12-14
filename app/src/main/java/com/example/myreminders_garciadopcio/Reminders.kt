package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color1
import com.example.myreminders_garciadopcio.ui.theme.Color4
import com.example.myreminders_garciadopcio.ui.theme.FontTittle

/**This is the main screen where all the reminders will be shown*/

@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun reminders(navHostController: NavHostController, onMenuIconClick: () -> Unit, viewModel: NotesViewModel){
/**In this function we will unite everything necessary*/
    Scaffold (
        topBar = {
            TopAppBar(//menu bar top of screen
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
                    ) //name that appears in the bar and its modifications
                },

                navigationIcon = {
                    IconButton(
                        onClick = {
                            onMenuIconClick()
                        }
                    ) {
                        Icon(//Three horizontal bars icon
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
                        Icon(//padlock icon
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
                        Icon(
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

        /**
         * In this column we call the ScreenPrincipalHidden method and with
         * the modifier we leave the space occupied by the TopAppBar
         */
        Column (Modifier.padding(padding)
        ){
            ScreenPrincipal(viewModel)
        }
    }

}

@Composable
fun ScreenPrincipal(viewModel: NotesViewModel) {
/**This function goes through the list of notes and checks that the state of the note is other than true, if so it prints them*/

    LazyColumn() {
        items(viewModel.notesList) { note ->

            if(note.state != true) {
                Card(
                    colors = CardDefaults.cardColors(Color4),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(0.8f)) {
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


                        Column(
                            modifier = Modifier
                                .weight(0.1f)
                                .padding(5.dp)
                        ) {
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
                            IconButton(
                                onClick = {
                                    viewModel.updateNoteTrue(note)
                                }
                            ) {
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
}