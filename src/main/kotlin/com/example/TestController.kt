package com.example;

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/")
    fun getIndex() {

        ResponseEntity(Test(), HttpStatus.OK)
    }

    @PostMapping("/getUserData")
    fun getUserData() = ResponseEntity(User(), HttpStatus.OK)
}
data class Test (
        val test: String = "index",
        val property: String = "other"
)
data class User (val login: String = "username", val password: String = "123")