package com.example.nammaraste.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammaraste.data.local.entity.Road
import com.example.nammaraste.data.repository.RoadRepository
import kotlinx.coroutines.launch

class RoadViewModel(
    private val repository: RoadRepository
) : ViewModel() {

    var roads = mutableStateListOf<Road>()
        private set

    init {
        loadRoads()
    }

    private fun loadRoads() {

        viewModelScope.launch {

            roads.clear()

            roads.addAll(
                repository.getAllRoads()
            )
        }
    }

    fun insertRoad(road: Road) {

        viewModelScope.launch {

            repository.insertRoad(road)

            loadRoads()
        }
    }

    suspend fun getAllRoads(): List<Road> {

        return repository.getAllRoads()
    }

    fun updateRoad(road: Road) {

        viewModelScope.launch {

            repository.updateRoad(road)

            loadRoads()
        }
    }
}