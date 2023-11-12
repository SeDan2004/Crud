package com.example.model

data class EmployeePosition(
    val fio: String,
    val dateOfBirthday: String,
    val position: String
)
data class CompanyDirectorResponse(val director: String)
data class CreateCompanyResponse(val id: String)