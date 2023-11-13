package com.example.exceptions

class EmployeeNotFound : Exception {
    constructor(msg: String) : super(msg)
}