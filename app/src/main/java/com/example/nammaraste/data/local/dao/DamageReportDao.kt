package com.example.nammaraste.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import com.example.nammaraste.data.local.entity.DamageReport

@Dao
interface DamageReportDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageReport(report: DamageReport)

    @Query("SELECT * FROM damage_reports")
    suspend fun getAllReports(): List<DamageReport>

    @Query("DELETE FROM damage_reports")
    suspend fun deleteAllReports()
    @Delete
    suspend fun deleteReport(report: DamageReport)
}