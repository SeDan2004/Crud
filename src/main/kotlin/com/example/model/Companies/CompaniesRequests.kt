package com.example.model.Companies

data class CreateCompanyRequest(
        val name: String,
        val director: String
)

data class UpdateDirectorRequest(
        val director: String
)