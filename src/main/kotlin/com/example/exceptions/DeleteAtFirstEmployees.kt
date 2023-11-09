package com.example.exceptions

import org.springframework.http.HttpStatus
import java.util.Date



class DeleteAtFirstEmployees : Exception {
    constructor(msg: String) : super(msg)
}
