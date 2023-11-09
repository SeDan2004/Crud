package com.example.exceptions

class ForeignKeyNotFound : Exception {
    constructor(msg: String) : super(msg)
}