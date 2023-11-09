package com.example.model

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