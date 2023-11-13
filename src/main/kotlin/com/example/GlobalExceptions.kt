package com.example

import com.example.exceptions.*
import com.example.model.Exceptions.*
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

    @ExceptionHandler(FailedCompanyDelete::class)
    fun failedCompanyDeleteException(ex: Exception) : ResponseEntity<FailedCompanyDeleteData> {
        val status = HttpStatus.NOT_ACCEPTABLE
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = FailedCompanyDeleteData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(BeforeDeleteEmployee::class)
    fun beforeDeleteEmployeeException(ex: Exception) : ResponseEntity<BeforeDeleteEmployeeData> {
        val status = HttpStatus.NOT_ACCEPTABLE
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = BeforeDeleteEmployeeData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.NOT_ACCEPTABLE)
    }

    @ExceptionHandler(EmployeeNotFound::class)
    fun employeeNotFoundException(ex: Exception) : ResponseEntity<EmployeeNotFoundData> {
        val status = HttpStatus.NOT_FOUND
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = EmployeeNotFoundData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(NotFoundPagNum::class)
    fun notFoundPagNumException(ex: Exception) : ResponseEntity<NotFoundPagNumData> {
        val status = HttpStatus.NOT_FOUND
        val currentDate = Date()
        val msg = ex.message
        val pojosClass = NotFoundPagNumData(status, currentDate, msg)

        return ResponseEntity(pojosClass, HttpStatus.NOT_FOUND)
    }
}