package com.example.nammaraste.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.nammaraste.viewmodel.DamageReportViewModel
import com.example.nammaraste.viewmodel.RoadViewModel

@Composable
fun BottomNavScreen(
    roadViewModel: RoadViewModel,
    damageReportViewModel: DamageReportViewModel
) {

    var selectedScreen by remember {
        mutableStateOf(0)
    }

    var selectedFilter by remember {
        mutableStateOf("ALL")
    }

    Scaffold(

        bottomBar = {

            NavigationBar {

                NavigationBarItem(
                    selected = selectedScreen == 0,
                    onClick = {
                        selectedScreen = 0
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Dashboard"
                        )
                    },
                    label = {
                        Text("Dashboard")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 1,
                    onClick = {
                        selectedScreen = 1
                        selectedFilter = "ALL"
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = "Roads"
                        )
                    },
                    label = {
                        Text("Roads")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 2,
                    onClick = {
                        selectedScreen = 2
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Map"
                        )
                    },
                    label = {
                        Text("Map")
                    }
                )

                NavigationBarItem(
                    selected = selectedScreen == 3,
                    onClick = {
                        selectedScreen = 3
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "Reports"
                        )
                    },
                    label = {
                        Text("Reports")
                    }
                )
            }
        }

    ) { paddingValues ->

        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

            when (selectedScreen) {

                0 -> DashboardScreen(

                    onCardClick = { filter ->

                        selectedFilter = filter

                        selectedScreen = 1
                    }
                )

                1 -> RoadDirectoryScreen(
                    roadViewModel = roadViewModel,
                    selectedFilter = selectedFilter
                )

                2 -> MapScreen(
                    damageReportViewModel = damageReportViewModel
                )

                3 -> ReportScreen(
                    damageReportViewModel = damageReportViewModel,
                    roadViewModel = roadViewModel
                )
            }
        }
    }
}