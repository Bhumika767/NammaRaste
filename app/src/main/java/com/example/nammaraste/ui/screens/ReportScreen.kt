package com.example.nammaraste.ui.screens

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.provider.MediaStore

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*

import androidx.compose.runtime.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.core.content.ContextCompat

import coil.compose.rememberAsyncImagePainter

import com.example.nammaraste.data.local.entity.DamageReport
import com.example.nammaraste.ui.theme.*
import com.example.nammaraste.viewmodel.DamageReportViewModel
import com.example.nammaraste.viewmodel.RoadViewModel
import com.google.android.gms.location.LocationServices

import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportScreen(
    roadViewModel: RoadViewModel,
    damageReportViewModel: DamageReportViewModel
) {

    val context = LocalContext.current

    var roadName by remember { mutableStateOf("") }

    var damageType by remember { mutableStateOf("") }

    var severity by remember { mutableStateOf("") }

    var location by remember {
        mutableStateOf("Fetching location...")
    }

    var latitude by remember {
        mutableStateOf(0.0)
    }

    var longitude by remember {
        mutableStateOf(0.0)
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    var showUploadMenu by remember {
        mutableStateOf(false)
    }

    val roads = roadViewModel.roads

    val reports = damageReportViewModel.reports

    val fusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context)

    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.RequestPermission()
        ) { isGranted ->

            if (isGranted) {

                if (
                    ContextCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {

                    fusedLocationClient.lastLocation
                        .addOnSuccessListener { currentLocation: Location? ->

                            currentLocation?.let {

                                latitude = it.latitude
                                longitude = it.longitude

                                try {

                                    val geocoder =
                                        Geocoder(
                                            context,
                                            Locale.getDefault()
                                        )

                                    val addresses =
                                        geocoder.getFromLocation(
                                            it.latitude,
                                            it.longitude,
                                            1
                                        )

                                    if (!addresses.isNullOrEmpty()) {

                                        location =
                                            addresses[0]
                                                .getAddressLine(0)
                                                ?: "Bangalore"

                                    } else {

                                        location = "Bangalore"
                                    }

                                } catch (e: Exception) {

                                    location = "Bangalore"
                                }
                            }
                        }
                }
            }
        }

    LaunchedEffect(Unit) {

        permissionLauncher.launch(
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.PickVisualMedia()
        ) { uri ->

            if (uri != null) {

                imageUri = uri
            }
        }

    val imageUriState = remember {

        mutableStateOf<Uri?>(null)
    }

    val cameraLauncher =
        rememberLauncherForActivityResult(
            contract =
                ActivityResultContracts.TakePicture()
        ) { success ->

            if (success) {

                imageUri = imageUriState.value
            }
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(LightPink)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "Report Damage",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = DarkPurple
        )

        Spacer(modifier = Modifier.height(20.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {

            OutlinedTextField(
                value = roadName,
                onValueChange = {},
                readOnly = true,

                label = {
                    Text("Select Road")
                },

                trailingIcon = {
                    ExposedDropdownMenuDefaults
                        .TrailingIcon(expanded = expanded)
                },

                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth(),

                colors = OutlinedTextFieldDefaults.colors(

                    focusedTextColor = DarkPurple,
                    unfocusedTextColor = DarkPurple,

                    focusedBorderColor = NeonPurple,
                    unfocusedBorderColor = RosePink,

                    focusedLabelColor = RosePink,
                    unfocusedLabelColor = TextMuted
                )
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {

                roads.forEach { road ->

                    DropdownMenuItem(
                        text = {
                            Text(road.roadName)
                        },

                        onClick = {

                            roadName = road.roadName

                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = damageType,
            onValueChange = {
                damageType = it
            },

            label = {
                Text("Damage Type")
            },

            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = DarkPurple,
                unfocusedTextColor = DarkPurple,

                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = RosePink,

                focusedLabelColor = RosePink,
                unfocusedLabelColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = severity,
            onValueChange = {
                severity = it
            },

            label = {
                Text("Severity")
            },

            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = DarkPurple,
                unfocusedTextColor = DarkPurple,

                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = RosePink,

                focusedLabelColor = RosePink,
                unfocusedLabelColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = location,
            onValueChange = {},
            readOnly = true,

            label = {
                Text("Live Location")
            },

            modifier = Modifier.fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(

                focusedTextColor = DarkPurple,
                unfocusedTextColor = DarkPurple,

                focusedBorderColor = NeonPurple,
                unfocusedBorderColor = RosePink,

                focusedLabelColor = RosePink,
                unfocusedLabelColor = TextMuted
            )
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                showUploadMenu = true
            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = NeonPurple
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Upload Damage Photo",
                color = WhiteShade
            )
        }

        DropdownMenu(
            expanded = showUploadMenu,
            onDismissRequest = {
                showUploadMenu = false
            }
        ) {

            DropdownMenuItem(
                text = {
                    Text("Capture Photo")
                },

                onClick = {

                    showUploadMenu = false

                    val contentValues =
                        ContentValues().apply {

                            put(
                                MediaStore.Images.Media.TITLE,
                                "Damage_Report"
                            )
                        }

                    val uri =
                        context.contentResolver.insert(
                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                            contentValues
                        )

                    imageUriState.value = uri

                    if (uri != null) {

                        cameraLauncher.launch(uri)
                    }
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Choose from Gallery")
                },

                onClick = {

                    showUploadMenu = false

                    galleryLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts
                                .PickVisualMedia.ImageOnly
                        )
                    )
                }
            )
        }

        if (imageUri != null) {

            Spacer(modifier = Modifier.height(16.dp))

            Image(
                painter =
                    rememberAsyncImagePainter(imageUri),

                contentDescription = null,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(
                        2.dp,
                        RosePink,
                        RoundedCornerShape(16.dp)
                    ),

                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                if (
                    roadName.isNotEmpty() &&
                    damageType.isNotEmpty() &&
                    severity.isNotEmpty()
                ) {

                    damageReportViewModel.insertReport(

                        DamageReport(
                            roadName = roadName,
                            damageType = damageType,
                            severity = severity,

                            location = location,

                            latitude = latitude,
                            longitude = longitude,

                            imageUri = imageUri?.toString() ?: ""
                        )
                    )

                    roadName = ""
                    damageType = ""
                    severity = ""
                    imageUri = null
                }
            },

            modifier = Modifier.fillMaxWidth(),

            colors = ButtonDefaults.buttonColors(
                containerColor = RosePink
            ),

            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "Submit Report",
                color = WhiteShade
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Submitted Reports",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = NeonPurple
        )

        Spacer(modifier = Modifier.height(16.dp))

        reports.forEach { report ->

            ReportCard(
                report = report,
                damageReportViewModel = damageReportViewModel
            )
        }
    }
}

@Composable
fun ReportCard(
    report: DamageReport,
    damageReportViewModel: DamageReportViewModel
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween
            ) {

                Text(
                    text = report.roadName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkPurple
                )

                TextButton(
                    onClick = {

                        damageReportViewModel
                            .deleteReport(report)
                    }
                ) {

                    Text(
                        text = "Delete",
                        color = CriticalRed,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Damage: ${report.damageType}",
                color = TextDark
            )

            Text(
                text = "Severity: ${report.severity}",
                color = CriticalRed
            )

            Text(
                text = "Location: ${report.location}",
                color = TextMuted
            )

            Text(
                text =
                    "Reported At: ${
                        SimpleDateFormat(
                            "dd/MM/yyyy hh:mm a",
                            Locale.getDefault()
                        ).format(Date(report.timestamp))
                    }",

                color = TextMuted
            )

            if (report.imageUri.isNotEmpty()) {

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter =
                        rememberAsyncImagePainter(
                            model = report.imageUri
                        ),

                    contentDescription = "Report Image",

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(12.dp)),

                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}