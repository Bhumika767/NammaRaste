package com.example.nammaraste.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nammaraste.R
import com.example.nammaraste.ui.theme.*

@Composable
fun DashboardScreen(
    onCardClick: (String) -> Unit
) {

    Column(
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
            )
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.nammaraste_banner
            ),
            contentDescription = "Banner",
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(24.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Smart Rural Infrastructure",
            fontSize = 18.sp,
            color = DarkPurple,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                DashboardCard(
                    title = "Total Roads",
                    value = "5"
                ) {
                    onCardClick("ALL")
                }
            }

            item {
                DashboardCard(
                    title = "Healthy Roads",
                    value = "1"
                ) {
                    onCardClick("Healthy")
                }
            }

            item {
                DashboardCard(
                    title = "Caution Roads",
                    value = "2"
                ) {
                    onCardClick("Caution")
                }
            }

            item {
                DashboardCard(
                    title = "Critical Roads",
                    value = "2"
                ) {
                    onCardClick("Critical")
                }
            }

            item {
                DashboardCard(
                    title = "Damage Reports",
                    value = "2"
                ) {
                    onCardClick("REPORTS")
                }
            }

            item {
                DashboardCard(
                    title = "Most Damaged Area",
                    value = "KR Market"
                ) {
                    onCardClick("Critical")
                }
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    value: String,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = title,
                fontSize = 18.sp,
                color = HotPink
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = value,
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = HotPink
            )
        }
    }
}