package com.example.myreminders_garciadopcio

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

/**On this screen we create the ModalNavigationDrawer which is the screen that moves to one side*/

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyModalNavigationDrawer(navHostController: NavHostController, viewModel: NotesViewModel) {
/**Here we have the function that we created to make the ModalNavigationDrawer and then use it on the main screen*/
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    /**These are the items that we are going to have in the ModalDrawer*/
    val items = listOf(
        Pair(Icons.Default.Add, "New Note"),
        Pair(Icons.Default.Lock, "Hidden")
    )
    val selectedItem = remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {

                /**Here we have the image that will be seen above the items*/
                Image(
                    painter = painterResource(id = R.drawable.logo_reminders),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(12.dp))
                items.forEach { (item, name) ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(text = name) },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                            selectedItem.value = Pair(item, name)
                            navHostController.navigate(name)
                        }
                    )
                }
            }
        }, content = {
            reminders(navHostController, onMenuIconClick = { scope.launch { drawerState.open() } }, viewModel)
        }
    )
}