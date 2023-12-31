package com.example.controllers

import com.example.model.Positions.CreatePositionRequest
import com.example.services.PositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/positions")
class PositionController {

    @Autowired
    lateinit var positionService: PositionService

    @GetMapping("/{position_id}")
    fun getPositionById(@PathVariable("position_id") positionId: Int) =
            positionService.getPositionById(positionId)

    @GetMapping("/employees/{position_id}")
    fun getEmployeesByPositionId(@PathVariable("position_id") positionId: Int) =
            positionService.getEmployeesByPositionId(positionId)

    @PostMapping
    fun addPosition(@RequestBody position: CreatePositionRequest) =
            positionService.addPosition(position)

    @DeleteMapping("/{position_id}")
    fun deletePosition(@PathVariable("position_id") positionId: Int) =
            positionService.deletePositionById(positionId)
}