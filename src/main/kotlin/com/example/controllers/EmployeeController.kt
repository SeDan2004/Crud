package com.example.controllers

import com.example.model.CreateEmployeeRequest
import com.example.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var EmployeeService: EmployeeService

    @GetMapping("/{employee_id}")
    fun getEmployeeById(@PathVariable("employee_id") userId: Int) =
            ResponseEntity(EmployeeService.getEmployeeById(userId), HttpStatus.OK)
    @GetMapping("/all")
    fun getAllEmployees() = ResponseEntity(EmployeeService.getAllEmployee(), HttpStatus.OK)
    @GetMapping("/short")
    fun getAllEmployeesShort() = ResponseEntity(EmployeeService.getAllEmployeeShort(), HttpStatus.OK)
    @PostMapping("/add")
    fun addEmployee(@RequestBody request: CreateEmployeeRequest) = EmployeeService.createEmployee(request)
    @DeleteMapping("/{employee_id}")
    fun deleteEmployee(@PathVariable("employee_id") userId: Int) = EmployeeService.deleteEmployee(userId)
}