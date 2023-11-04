package com.example.controllers

import com.example.services.PositionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PositionController {

    @Autowired
    lateinit var positionService: PositionService

    @GetMapping("/get_employees_by_position_id")
    fun getEmployeesByPositionId(@RequestParam("positionId") positionId: Int) =
            positionService.getEmployeesByPositionId(positionId)

    @PostMapping("/add_position")
    fun addPosition(@RequestParam("position") position: String) =
            positionService.addPosition(position)

    @PostMapping("/delete_position")
    fun deletePosition(@RequestParam("positionId") positionId: Int) =
            positionService.deletePositionById(positionId)
}