package com.example.nammaraste

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.lifecycle.lifecycleScope
import com.example.nammaraste.data.local.DatabaseProvider
import com.example.nammaraste.data.local.entity.Road
import com.example.nammaraste.data.repository.DamageReportRepository
import com.example.nammaraste.data.repository.RoadRepository
import com.example.nammaraste.ui.screens.BottomNavScreen
import com.example.nammaraste.ui.screens.SplashScreen
import com.example.nammaraste.ui.theme.NammaRasteTheme
import com.example.nammaraste.viewmodel.DamageReportViewModel
import com.example.nammaraste.viewmodel.RoadViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var roadViewModel: RoadViewModel
    private lateinit var damageReportViewModel: DamageReportViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = DatabaseProvider.getDatabase(this)

        val roadRepository = RoadRepository(
            database.roadDao()
        )

        val damageReportRepository = DamageReportRepository(
            database.damageReportDao()
        )

        roadViewModel = RoadViewModel(roadRepository)

        damageReportViewModel = DamageReportViewModel(
            damageReportRepository
        )

        lifecycleScope.launch {

            val roads = roadViewModel.getAllRoads()

            if (roads.isEmpty()) {

                roadViewModel.insertRoad(
                    Road(
                        roadName = "Hosur Road",
                        totalLengthKm = 18.5,
                        contractorName = "Bangalore Infra Pvt Ltd",
                        contractorContact = "9876543210",
                        warrantyEndDate = "2028-12-31",
                        healthStatus = "Critical",
                        talukaName = "Electronic City"
                    )
                )

                roadViewModel.insertRoad(
                    Road(
                        roadName = "Bannerghatta Road",
                        totalLengthKm = 14.2,
                        contractorName = "Metro Builders",
                        contractorContact = "9123456780",
                        warrantyEndDate = "2027-08-20",
                        healthStatus = "Caution",
                        talukaName = "Bengaluru South"
                    )
                )

                roadViewModel.insertRoad(
                    Road(
                        roadName = "Kanakapura Road",
                        totalLengthKm = 21.4,
                        contractorName = "Green Roads Ltd",
                        contractorContact = "9988776655",
                        warrantyEndDate = "2029-01-10",
                        healthStatus = "Healthy",
                        talukaName = "Kanakapura"
                    )
                )

                roadViewModel.insertRoad(
                    Road(
                        roadName = "Mysore Road",
                        totalLengthKm = 25.0,
                        contractorName = "Highway Tech Builders",
                        contractorContact = "9876501234",
                        warrantyEndDate = "2026-05-15",
                        healthStatus = "Critical",
                        talukaName = "Kengeri"
                    )
                )

                roadViewModel.insertRoad(
                    Road(
                        roadName = "Sarjapura Road",
                        totalLengthKm = 17.8,
                        contractorName = "Urban Connect Infra",
                        contractorContact = "9012345678",
                        warrantyEndDate = "2028-03-25",
                        healthStatus = "Caution",
                        talukaName = "Sarjapura"
                    )
                )
            }
        }

        setContent {

            NammaRasteTheme {

                var showSplash by remember {
                    mutableStateOf(true)
                }

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (showSplash) {

                        SplashScreen(

                            onSplashFinished = {

                                showSplash = false
                            }
                        )

                    } else {

                        BottomNavScreen(
                            roadViewModel = roadViewModel,
                            damageReportViewModel = damageReportViewModel
                        )
                    }
                }
            }
        }
    }
}