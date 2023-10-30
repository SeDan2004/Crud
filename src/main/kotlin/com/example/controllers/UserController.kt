package com.example.controllers

import com.example.model.CreateEmployeeRequest
import com.example.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/get_user")
    fun getUserById(@RequestParam("id") id: Int) {
        //userService.userRepository.getUserById(id)
    }

    @PostMapping("/add_user")
    fun addUser(@RequestBody request: CreateEmployeeRequest) = userService.createEmployee(request)

    @PostMapping("/delete_user")
    fun deleteUser(@RequestParam("id") id: Int) {

    }
}

data class emp(
        var fio: String,
        var dateOfBirthday: String,
        var positionId: Int,
        var companyId: Int
)