package com.example.services

import com.example.db.schema.tables.records.JPositionsRecord
import com.example.model.CreatePositionRequest
import com.example.repositories.PositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PositionService {

    @Autowired
    lateinit var positionRepository: PositionRepository

    fun getEmployeesByPositionId(positionId: Int) =
            positionRepository.getEmployees(positionId)

    fun addPosition(position: CreatePositionRequest) =
            JPositionsRecord().apply {
                this.position = position.position
            }.let(positionRepository::add)

    fun deletePositionById(positionId: Int) =
            positionRepository.delete(positionId)
}