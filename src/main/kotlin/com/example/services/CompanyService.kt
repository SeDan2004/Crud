package com.example.services

import com.example.db.schema.tables.records.JCompanyRecord
import com.example.model.CreateCompanyRequest
import com.example.repositories.CompanyRepository
import com.example.repositories.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CompanyService {

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Autowired
    private lateinit var employeeRepository: EmployeeRepository

    fun getByCompanyId(id: Int) = companyRepository.getById(id)

    fun getEmployeesByCompanyId(id: Int) = employeeRepository.getByCompanyId(id)

    fun getCompanyDirector(companyId: Int) = companyRepository.getDirector(companyId)

    fun updateCompanyDirector(companyId: Int, director: String) =
            companyRepository.updateDirector(companyId, director)

    fun addCompany(request: CreateCompanyRequest) =
            JCompanyRecord().apply {
                name = request.name
                director = request.director
            }.let (companyRepository::add)

    fun deleteCompany(companyId: Int) = companyRepository.delete(companyId)

    fun deleteAllCompanyEmployees(companyId: Int) =
            employeeRepository.deleteAllCompanyEmployees(companyId)
}