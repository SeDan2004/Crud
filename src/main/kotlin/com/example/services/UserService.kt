package com.example.services

import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.model.CreateEmployeeRequest
import com.example.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    fun getEmployeeById(employeeId: Int) = userRepository.get(employeeId)
    fun getAllEmployee() = userRepository.getAll()
    fun getAllEmployeeShort() = userRepository.getAllShort()
    fun createEmployee(request: CreateEmployeeRequest) =
            JEmployeesRecord().apply {
                fio = request.fio
                dateOfBirthday = request.dateOfBirthday
                positionId = request.positionId
                companyId = request.companyId
            }.let(userRepository::create)

    fun deleteEmployee(employeeId: Int) = userRepository.delete(employeeId)
}