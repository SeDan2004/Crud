package com.example.services

import com.example.db.schema.tables.records.JPositionsRecord
import com.example.exceptions.BeforeDeleteEmployee
import com.example.model.CreatePositionRequest
import com.example.repositories.EmployeeRepository
import com.example.repositories.PositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PositionService {

    @Autowired
    lateinit var positionRepository: PositionRepository

    @Autowired
    lateinit var employeeRepository: EmployeeRepository

    fun getPositionById(id: Int) =
            positionRepository.getById(id)

    fun getEmployeesByPositionId(id: Int) =
            employeeRepository.getByPositionId(id)

    fun addPosition(position: CreatePositionRequest) =
            JPositionsRecord().apply {
                name = position.position
            }.let(positionRepository::save)

    fun deletePositionById(id: Int) {
        val msg: String

        if (employeeRepository.checkByPositionId(id)) {
            msg = "Прежде чем удалить должность, удалите сотрудников занимающих эту должность!"
            throw BeforeDeleteEmployee(msg)
        }

        positionRepository.deleteById(id)
    }
}