package com.example.nammaraste.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import com.example.nammaraste.R
import com.example.nammaraste.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {

    LaunchedEffect(Unit) {

        delay(2500)

        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        LightPink,
                        LightPurple,
                        WhiteShade
                    )
                )
            ),

        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.splash_screen
                ),

                contentDescription = "Splash Screen",

                modifier = Modifier
                    .size(260.dp),

                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Namma-Raste Health",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPurple
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Smart Rural Road Monitoring",
                fontSize = 16.sp,
                color = HotPink
            )
        }
    }
}