package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
/**This is the screen that will allow us to enter new notes*/
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun new(navHostController: NavHostController, viewModel: NotesViewModel){
/**In this function we put together everything we want to show on the screen*/

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
                newHorizontal(navHostController, viewModel)


        }
        else -> {
            newVertical(navHostController, viewModel)
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun newVertical(navHostController: NavHostController, viewModel: NotesViewModel){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (

        topBar = {
            TopAppBarNew(navHostController = navHostController)
        }
    ) {
        /**In this column we create two OutlinedTextField so that the client can enter a title and a description for their new note*/
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = it.calculateTopPadding())
        ) {

            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }

            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = {
                    Text("Title")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                singleLine = true,
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    //textColor = LocalContentColor.current
                ),
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = {
                    Text("Description", )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(200.dp),
                singleLine = false,
                colors = outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    //textColor = LocalContentColor.current
                ),
            )

            /**This SnackbarHost shows a message once the filled information has been sent*/
            SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(CenterHorizontally))

            /**This button sends the information filled out by the client to the database.*/
            Button(
                onClick = {
                    val note = Note(title, description)
                    viewModel.addNote(note)

                    scope.launch { snackbarHostState.showSnackbar("The note has been added successfully")}

                    description = ""
                    title = ""
                },
                modifier = Modifier
                    .padding(10.dp)
                    .align(CenterHorizontally)
            ) {
                Text(text = "Add note", color = Color.Black)
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun newHorizontal(navHostController: NavHostController, viewModel: NotesViewModel){
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold (

        topBar = {
            TopAppBarNew(navHostController = navHostController)
        }
    ) {

        var title by remember { mutableStateOf("") }
        var description by remember { mutableStateOf("") }

        /**In this column we create two OutlinedTextField so that the client can enter a title and a description for their new note*/
        Row {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .weight(1f)
            ) {



                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = {
                        Text("Title")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    singleLine = true,
                    colors = outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        //textColor = LocalContentColor.current
                    ),
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = {
                        Text("Description",)
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
//                    .height(200.dp)
                    singleLine = false,
                    colors = outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        //textColor = LocalContentColor.current
                    ),
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = it.calculateTopPadding())
                    .weight(1f),
                horizontalAlignment = CenterHorizontally,
                verticalArrangement = Center
            ){
                /**This SnackbarHost shows a message once the filled information has been sent*/
                SnackbarHost(hostState = snackbarHostState, modifier = Modifier.align(CenterHorizontally))

                /**This button sends the information filled out by the client to the database.*/
                Button(
                    onClick = {
                        val note = Note(title, description)
                        viewModel.addNote(note)

                        scope.launch { snackbarHostState.showSnackbar("The note has been added successfully")}

                        description = ""
                        title = ""
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(CenterHorizontally)
                ) {
                    Text(text = "Add note", color = Color.Black)
                }
            }


        }


        }
    }


