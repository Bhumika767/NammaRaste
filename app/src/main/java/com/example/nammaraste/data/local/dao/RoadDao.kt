package com.example.nammaraste.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nammaraste.data.local.entity.Road

@Dao
interface RoadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoad(road: Road)

    @Query("SELECT * FROM roads")
    suspend fun getAllRoads(): List<Road>

    @Update
    suspend fun updateRoad(road: Road)
}