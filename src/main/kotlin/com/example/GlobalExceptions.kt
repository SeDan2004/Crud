package com.example

import com.example.exceptions.DeleteAtFirstEmployees
import com.example.exceptions.ForeignKeyNotFound
import com.example.model.DeleteAtFirstEmployeesData
import com.example.model.ForeignKeyNotFoundData
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*


@RestControllerAdvice
class GlobalExceptions {

    @ExceptionHandler(DeleteAtFirstEmployees::class)
    fun deleteAtFirstEmployeesEx(ex: Exception) : ResponseEntity<DeleteAtFirstEmployeesData> {
        val status = HttpStatus.FORBIDDEN
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = DeleteAtFirstEmployeesData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.FORBIDDEN)
    }

    @ExceptionHandler(ForeignKeyNotFound::class)
    fun foreignKeyNotFoundException(ex: Exception) : ResponseEntity<ForeignKeyNotFoundData> {
        val status = HttpStatus.NOT_FOUND
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = ForeignKeyNotFoundData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.NOT_FOUND)
    }
}