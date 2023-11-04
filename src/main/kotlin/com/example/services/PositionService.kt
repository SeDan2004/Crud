package com.example.services

import com.example.repositories.PositionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PositionService {

    @Autowired
    lateinit var positionRepository: PositionRepository

    fun getEmployeesByPositionId(positionId: Int) =
            positionRepository.getEmployees(positionId)

    fun addPosition(position: String) =
            positionRepository.add(position)

    fun deletePositionById(positionId: Int) =
            positionRepository.delete(positionId)
}