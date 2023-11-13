package com.example.model.Employees

import java.time.LocalDate

data class CreateEmployeeRequest(
        val fio: String,
        val dateOfBirthday: LocalDate,
        val positionId: Int,
        val companyId: Int
)

data class UpdateEmployeePositionRequest(
        val positionId: Int
)

data class UpdateEmployeeCompanyRequest(
        val companyId: Int
)

data class PagEmplRequest(
     val pairNum: Int,
     val emplCount: Int
)