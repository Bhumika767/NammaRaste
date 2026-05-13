package com.example.nammaraste.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nammaraste.data.local.dao.DamageReportDao
import com.example.nammaraste.data.local.dao.RoadDao
import com.example.nammaraste.data.local.entity.DamageReport
import com.example.nammaraste.data.local.entity.Road

@Database(
    entities = [Road::class, DamageReport::class],
    version = 5,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun roadDao(): RoadDao

    abstract fun damageReportDao(): DamageReportDao
}