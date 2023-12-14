package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color4

/**This screen shows the notes that we have completed or hidden*/
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun hidden(navHostController: NavHostController,  viewModel: NotesViewModel){
/**In this function we compose the screen*/

    Scaffold (
        /**We insert the TopAppBar that we have in the TopAppBarHidden method*/
        topBar = { TopAppBarHidden(navHostController = navHostController) }
    ){
        /**
         * In this column we call the method ScreenPrincipalHidden and with
         * the modifier we calculate the space occupied by the TopAppBar so that the contents do not overlap
         */
        Column (
            Modifier.padding(top = it.calculateTopPadding())
        ){
            ScreenPrincipalHidde(viewModel)
        }

    }

}

@Composable
fun ScreenPrincipalHidde(viewModel: NotesViewModel) {
/**In this function we iterate over the list of notes, we check if the state of the note is true and if so it prints them*/

    LazyColumn() {
        items(viewModel.notesList) { note ->
            if(note.state == true) {
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

                        /**Here we put the two icons that are to the right of the card*/
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
                                    viewModel.updateNoteFalse(note)
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