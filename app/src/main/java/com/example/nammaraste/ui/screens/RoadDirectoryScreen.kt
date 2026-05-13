package com.example.nammaraste.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nammaraste.data.local.entity.Road
import com.example.nammaraste.ui.theme.*
import com.example.nammaraste.viewmodel.RoadViewModel

@Composable
fun RoadDirectoryScreen(
    roadViewModel: RoadViewModel,
    selectedFilter: String = "ALL"
) {

    val allRoads = roadViewModel.roads

    val filteredRoads = when (selectedFilter) {

        "Healthy" -> {
            allRoads.filter {
                it.healthStatus == "Healthy"
            }
        }

        "Caution" -> {
            allRoads.filter {
                it.healthStatus == "Caution"
            }
        }

        "Critical" -> {
            allRoads.filter {
                it.healthStatus == "Critical"
            }
        }

        else -> {
            allRoads
        }
    }

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

        Text(
            text = "Road Directory",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Showing: $selectedFilter Roads",
            color = HotPink,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn {

            items(filteredRoads) { road ->

                RoadCard(road)
            }
        }
    }
}

@Composable
fun RoadCard(road: Road) {

    val statusColor = when (road.healthStatus) {
        "Healthy" -> HealthyGreen
        "Caution" -> CautionAmber
        else -> CriticalRed
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(
            containerColor = LightPurple
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = road.roadName,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkPurple
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = road.healthStatus,
                color = statusColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Taluka: ${road.talukaName}",
                color = TextDark
            )

            Text(
                text = "Length: ${road.totalLengthKm} km",
                color = TextDark
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Contractor: ${road.contractorName}",
                fontWeight = FontWeight.SemiBold,
                color = HotPink
            )

            Text(
                text = "Contact: ${road.contractorContact}",
                color = TextDark
            )

            Text(
                text = "Warranty Till: ${road.warrantyEndDate}",
                color = TextMuted
            )
        }
    }
}