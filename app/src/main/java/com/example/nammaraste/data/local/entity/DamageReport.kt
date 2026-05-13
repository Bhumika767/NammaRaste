package com.example.nammaraste.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "damage_reports")
data class DamageReport(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val roadName: String,

    val damageType: String,

    val severity: String,

    // READABLE LOCATION
    val location: String,

    // EXACT GPS COORDINATES
    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val imageUri: String = "",

    val timestamp: Long = System.currentTimeMillis()
)