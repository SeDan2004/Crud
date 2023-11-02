package com.example.services

import com.example.db.schema.tables.pojos.JCompany
import com.example.db.schema.tables.records.JCompanyRecord
import com.example.model.CreateCompanyRequest
import com.example.repositories.CompanyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CompanyService {

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    fun getCompanyById(companyId: Int) = companyRepository.getById(companyId)

    fun getCompanyDirector(companyId: Int) = companyRepository.getDirector(companyId)

    fun updateCompanyDirector(companyId: Int, director: String) =
            companyRepository.updateDirector(companyId, director)

    fun getCompanyEmployee(companyId: Int) = companyRepository.getEmployees(companyId)

    fun addCompany(request: CreateCompanyRequest) =
            JCompanyRecord().apply {
                name = request.name
                director = request.director
            }.let {companyRepository.add(it.name, it.director)}

    fun deleteCompany(companyId: Int) = companyRepository.delete(companyId)
}