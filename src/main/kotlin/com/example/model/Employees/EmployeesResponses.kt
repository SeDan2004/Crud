package com.example.model.Employees

import java.time.LocalDate

data class CreateEmployeeResponse(val id: Int)
data class EmployeeShort(val id: String, val fio: String)
data class EmployeeShort2(val fio: String, val dateOfBirthday: LocalDate)
data class GridResponse(
        val list: List<Any>,
        val totalCount: Int
)