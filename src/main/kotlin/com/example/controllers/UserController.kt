package com.example.controllers

import com.example.model.CreateEmployeeRequest
import com.example.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @GetMapping("get_user")
    fun findUser(@RequestParam("id") userId: Int) =
            ResponseEntity(userService.getEmployeeById(userId), HttpStatus.OK)
    @GetMapping("/get_all_users")
    fun getAllUsers() = ResponseEntity(userService.getAllEmployee(), HttpStatus.OK)
    @GetMapping("/get_all_users_short")
    fun getAllUsersShort() = ResponseEntity(userService.getAllEmployeeShort(), HttpStatus.OK)
    @PostMapping("/add_user")
    fun addUser(@RequestBody request: CreateEmployeeRequest) = userService.createEmployee(request)
    @PostMapping("/delete_user")
    fun deleteUser(@RequestParam("id") userId: Int) = userService.deleteEmployee(userId)
}