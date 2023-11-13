package com.example.model.Exceptions

import org.springframework.http.HttpStatus
import java.util.*

data class DeleteAtFirstEmployeesData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)

data class ForeignKeyNotFoundData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)

data class FailedCompanyDeleteData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)

data class BeforeDeleteEmployeeData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)

data class EmployeeNotFoundData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)

data class NotFoundPagNumData(
        val status: HttpStatus,
        val date: Date,
        val message: String?
)