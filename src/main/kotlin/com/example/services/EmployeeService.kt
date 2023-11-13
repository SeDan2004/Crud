package com.example.services

import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.exceptions.EmployeeNotFound
import com.example.exceptions.ForeignKeyNotFound
import com.example.exceptions.NotFoundPagNum
import com.example.model.Employees.*
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
    private lateinit var positionRepository: PositionRepository

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    fun getEmployeeById(employeeId: Int) = employeeRepository.getById(employeeId)

    fun getAllEmployee() = employeeRepository.getAll()

    fun getAllEmployeeCount() = employeeRepository.getCountAll()

    fun getAllEmployeeShort() = employeeRepository.getAllShort()

    fun getEmplByPagNum(request: PagEmplRequest) : GridResponse {
        val pairNum = request.pairNum
        val emplCount = request.emplCount
        val allEmpl = getAllEmployeeCount()
        val allPagCount = Math.ceil(allEmpl.toDouble() / emplCount)
        val offset = emplCount * (pairNum - 1)

        if (pairNum < 1 || pairNum > allPagCount) {
            throw NotFoundPagNum("Uncorrect pagination num value!")
        }

        val lst = employeeRepository.getByLimitAndOffset(emplCount, offset)
        val totalCount = lst.size

        return GridResponse(lst, totalCount)
    }

    fun createEmployee(request: CreateEmployeeRequest): CreateEmployeeResponse {
        checkFkExist(request)

        val employee = JEmployeesRecord().apply {
            fio = request.fio
            dateOfBirthday = request.dateOfBirthday
            positionId = request.positionId
            companyId = request.companyId
        }.let(employeeRepository::save)

        return CreateEmployeeResponse(employee.id)
    }

    fun checkEmployeeExists(id: Int) {
        if (!employeeRepository.isExistsById(id))
            throw EmployeeNotFound("Employee not found!")
    }

    fun updateEmployeePosition(id: Int, request: UpdateEmployeePositionRequest) {
        val positionId = request.positionId

        checkEmployeeExists(id)
        checkFkExistPosition(positionId)
        employeeRepository.updatePosition(id, positionId)
    }

    fun updateEmployeeCompany(id: Int, request: UpdateEmployeeCompanyRequest) {
        val companyId = request.companyId

        checkEmployeeExists(id)
        checkFkExistCompany(companyId)
        employeeRepository.updateCompany(id, companyId)
    }

    private fun checkFkExist(request: CreateEmployeeRequest) {
        checkFkExistPosition(request.positionId)
        checkFkExistCompany(request.companyId)
    }

    fun checkFkExistPosition(positionId: Int) {
        if (!positionRepository.isExistsById(positionId))
            notFound("Foreign key not found in positions!")
    }

    fun checkFkExistCompany(companyId: Int) {
        if (!companyRepository.isExistsById(companyId))
            notFound("Foreign key not found in companies!")
    }

    fun notFound(msg: String): Nothing = throw ForeignKeyNotFound(msg)

    fun deleteEmployee(employeeId: Int) = employeeRepository.deleteById(employeeId)
}