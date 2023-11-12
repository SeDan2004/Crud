package com.example.model

import java.time.LocalDate

data class CreateEmployeeResponse(val id: Int)
data class EmployeeShort(val id: String, val fio: String)
data class EmployeeShort2(val fio: String, val dateOfBirthday: LocalDate)