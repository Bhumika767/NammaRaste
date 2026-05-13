package com.example.nammaraste.data.repository

import com.example.nammaraste.data.local.dao.DamageReportDao
import com.example.nammaraste.data.local.entity.DamageReport

class DamageReportRepository(
    private val damageReportDao: DamageReportDao
) {

    suspend fun insertDamageReport(report: DamageReport) {

        damageReportDao.insertDamageReport(report)
    }

    suspend fun getAllReports(): List<DamageReport> {

        return damageReportDao.getAllReports()
    }

    suspend fun deleteAllReports() {

        damageReportDao.deleteAllReports()
    }

    suspend fun deleteReport(report: DamageReport) {

        damageReportDao.deleteReport(report)
    }
}