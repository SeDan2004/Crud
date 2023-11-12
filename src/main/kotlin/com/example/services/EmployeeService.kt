package com.example.services

import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.exceptions.ForeignKeyNotFound
import com.example.model.CreateEmployeeRequest
import com.example.model.CreateEmployeeResponse
import com.example.repositories.CompanyRepository
import com.example.repositories.EmployeeRepository
import com.example.repositories.PositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeService {

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    @Autowired
    private lateinit var positionRepository : PositionRepository

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    fun getEmployeeById(employeeId: Int) = employeeRepository.getById(employeeId)
    fun getAllEmployee() = employeeRepository.getAll()
    fun getAllEmployeeShort() = employeeRepository.getAllShort()
    fun createEmployee(request: CreateEmployeeRequest) : CreateEmployeeResponse {
        val msg: String

        if (!positionRepository.checkById(request.positionId)) {
            msg = "Внешний ключ id(${request.positionId}) не найден в таблице positions!"
            throw ForeignKeyNotFound(msg)
        }

        if (!companyRepository.checkById(request.companyId)) {
            msg = "Внешний ключ id(${request.companyId}) не найден в таблице companies!"
            throw ForeignKeyNotFound(msg)
        }

        return JEmployeesRecord().apply {
            fio = request.fio
            dateOfBirthday = request.dateOfBirthday
            positionId = request.positionId
            companyId = request.companyId
        }.let(employeeRepository::insert)
    }
    fun deleteEmployee(employeeId: Int) = employeeRepository.deleteById(employeeId)
}