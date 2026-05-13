package com.example.nammaraste.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import com.example.nammaraste.viewmodel.DamageReportViewModel

import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    damageReportViewModel: DamageReportViewModel
) {

    val reports = damageReportViewModel.reports

    val firstReportLocation = if (reports.isNotEmpty()) {

        LatLng(
            reports[0].latitude,
            reports[0].longitude
        )

    } else {

        LatLng(12.9716, 77.5946)
    }

    val cameraPositionState = rememberCameraPositionState {

        position = CameraPosition.fromLatLngZoom(
            firstReportLocation,
            12f
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {

        reports.forEach { report ->

            val reportLocation = LatLng(
                report.latitude,
                report.longitude
            )

            val markerColor = when (
                report.severity.lowercase()
            ) {

                "low" -> BitmapDescriptorFactory.HUE_GREEN

                "medium" -> BitmapDescriptorFactory.HUE_YELLOW

                "critical" -> BitmapDescriptorFactory.HUE_RED

                else -> BitmapDescriptorFactory.HUE_ORANGE
            }

            Marker(
                state = rememberMarkerState(
                    position = reportLocation
                ),

                title = report.roadName,

                snippet =
                    "${report.damageType} | ${report.location}",

                icon = BitmapDescriptorFactory
                    .defaultMarker(markerColor)
            )
        }
    }
}