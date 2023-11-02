package com.example.model

import java.time.LocalDate

data class CreateEmployeeRequest(
        val fio: String,
        val dateOfBirthday: LocalDate,
        val positionId: Int,
        val companyId: Int
)
