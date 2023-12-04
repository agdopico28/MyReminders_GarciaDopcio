package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myreminders_garciadopcio.ui.theme.Color4


val articulos = mutableStateListOf<Note>()


@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
fun reminders(navHostController: NavHostController){
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold (
        topBar = { MyTopAppBar(navHostController = navHostController, drawerState, scope) }
    ){
        Column (Modifier.padding(top = it.calculateTopPadding())
        ){
            PantallaPrincipal()
        }

        MyModalNavigationDrawer(navController = navHostController, drawerState, scope)
        //MyFLoadinActionButtonAdd(navHostController = navHostController)
    }

}

@Composable
fun PantallaPrincipal() {
    LazyColumn() {
        items(articulos) { note ->
            Card(
                colors = CardDefaults.cardColors(Color4),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column() {
                    Text(
                        text =  buildAnnotatedString {
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

            }
        }
    }
}