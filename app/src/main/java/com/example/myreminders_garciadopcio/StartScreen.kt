package com.example.myreminders_garciadopcio

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myreminders_garciadopcio.ui.theme.Color1
import com.example.myreminders_garciadopcio.ui.theme.FontTittle
import kotlinx.coroutines.delay

@Composable
fun startScreen(navController: NavController) {

    val my_name = "Amalia Garcia Dopcio"
    val value by rememberInfiniteTransition().animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )
    // Esta función muestra la pantalla de inicio.

    // Puedes personalizar este contenido según tus necesidades.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color1),
        contentAlignment = Alignment.Center
    ) {
        Box(modifier = Modifier
            .graphicsLayer {
                scaleX = value
                scaleY = value
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_reminders),
                contentDescription = null
            )
        }


        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontSize = 10.sp)) {
                    append("Create by:\n")
                }
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold,fontSize = 15.sp)) {
                    append(my_name)
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center
//            fontFamily = FontTittle
        )
    }

    // Utilizamos LaunchedEffect para iniciar un temporizador y cambiar a la siguiente pantalla.
    LaunchedEffect(true) {
        delay(2000) // Cambié el tiempo de espera a 2 segundos para el ejemplo
        // Navegamos a la pantalla "Reminders_create" después de que ha pasado el tiempo deseado.
        navController.navigate("Reminders_show")
    }
}
