package com.example.controllers

import com.example.model.Employees.CreateEmployeeRequest
import com.example.model.Employees.PagEmplRequest
import com.example.model.Employees.UpdateEmployeeCompanyRequest
import com.example.model.Employees.UpdateEmployeePositionRequest
import com.example.services.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController {

    @Autowired
    private lateinit var employeeService: EmployeeService

    @GetMapping("/{employee_id}")
    fun getEmployeeById(@PathVariable("employee_id") id: Int) =
            ResponseEntity(employeeService.getEmployeeById(id), HttpStatus.OK)

    @GetMapping("/all")
    fun getAllEmployees() = ResponseEntity(employeeService.getAllEmployee(), HttpStatus.OK)

    @GetMapping("/short")
    fun getAllEmployeesShort() = ResponseEntity(employeeService.getAllEmployeeShort(), HttpStatus.OK)

    @GetMapping("/pagination")
    fun getEmplByPagNum(@RequestBody pagEmplRequest: PagEmplRequest) =
            ResponseEntity(employeeService.getEmplByPagNum(pagEmplRequest), HttpStatus.OK)

    @PostMapping
    fun addEmployee(@RequestBody request: CreateEmployeeRequest) = employeeService.createEmployee(request)

    @PatchMapping("/position/{employee_id}")
    fun updateEmployeePosition(@PathVariable("employee_id") id: Int,
                               @RequestBody updateEmployeePositionRequest: UpdateEmployeePositionRequest) =
            employeeService.updateEmployeePosition(id, updateEmployeePositionRequest)

    @PatchMapping("/company/{employee_id}")
    fun updateEmployeeCompany(@PathVariable("employee_id") id: Int,
                              @RequestBody updateEmployeeCompanyRequest: UpdateEmployeeCompanyRequest) =
            employeeService.updateEmployeeCompany(id, updateEmployeeCompanyRequest)

    @DeleteMapping("/{employee_id}")
    fun deleteEmployee(@PathVariable("employee_id") id: Int) = employeeService.deleteEmployee(id)
}