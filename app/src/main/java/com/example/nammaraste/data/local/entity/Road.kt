package com.example.nammaraste.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "roads")
data class Road(

    @PrimaryKey(autoGenerate = true)
    val roadId: Int = 0,

    val roadName: String,

    val totalLengthKm: Double,

    val contractorName: String,

    val contractorContact: String?,

    val warrantyEndDate: String,

    var healthStatus: String,

    val talukaName: String,

    var reportCount: Int = 0
)