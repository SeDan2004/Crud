package com.example.model

import java.time.LocalDate
import java.util.*

data class CreateEmployeeRequest(
        val fio: String,
        val dateOfBirthday: LocalDate,
        val positionId: Int,
        val companyId: Int
)
