package com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example"])
class CrudApplication

fun main(args: Array<String>) {
	runApplication<CrudApplication>(*args)
}
