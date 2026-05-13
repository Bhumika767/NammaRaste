package com.example.nammaraste.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nammaraste.data.local.entity.DamageReport
import com.example.nammaraste.data.repository.DamageReportRepository
import kotlinx.coroutines.launch

class DamageReportViewModel(
    private val repository: DamageReportRepository
) : ViewModel() {

    var reports = mutableStateListOf<DamageReport>()
        private set

    init {
        loadReports()
    }

    private fun loadReports() {
        viewModelScope.launch {

            reports.clear()

            reports.addAll(
                repository.getAllReports()
            )
        }
    }

    fun insertReport(report: DamageReport) {

        viewModelScope.launch {

            repository.insertDamageReport(report)

            loadReports()
        }
    }

    // NEW DELETE SINGLE REPORT FUNCTION
    fun deleteReport(report: DamageReport) {

        viewModelScope.launch {

            repository.deleteReport(report)

            loadReports()
        }
    }

    fun deleteAllReports() {

        viewModelScope.launch {

            repository.deleteAllReports()

            loadReports()
        }
    }
}