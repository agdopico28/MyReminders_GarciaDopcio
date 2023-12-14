package com.example.myreminders_garciadopcio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.core.view.ViewCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.myreminders_garciadopcio.ui.theme.MyReminders_GarciaDopcioTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        setContent {

            MyReminders_GarciaDopcioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val  viewModel = viewModel<NotesViewModel>()
                    viewModel.addNoteToLocalList()

                    NavHost(navController = navController, startDestination = "StartScreen") {
                        composable(
                            "StartScreen",
                            //Transition between screens
                            exitTransition = {
                                fadeOut(animationSpec = tween(1000))
                            },
                        ) {
                            startScreen(navController)
                        }

                        composable("Reminders_show") { MyModalNavigationDrawer(navController, viewModel) }

                        composable("New Note") { new(navController, viewModel) }

                        composable("Hidden") { hidden(navController, viewModel) }

                    }
                }
            }
        }
    }
}