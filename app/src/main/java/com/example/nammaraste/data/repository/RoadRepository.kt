package com.example.nammaraste.data.repository

import com.example.nammaraste.data.local.dao.RoadDao
import com.example.nammaraste.data.local.entity.Road

class RoadRepository(
    private val roadDao: RoadDao
) {

    suspend fun insertRoad(road: Road) {

        roadDao.insertRoad(road)
    }

    suspend fun getAllRoads(): List<Road> {

        return roadDao.getAllRoads()
    }

    suspend fun updateRoad(road: Road) {

        roadDao.updateRoad(road)
    }
}