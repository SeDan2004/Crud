package com.example.services

import com.example.db.schema.tables.records.JCompaniesRecord
import com.example.exceptions.FailedCompanyDelete
import com.example.model.CreateCompanyRequest
import com.example.model.CreateCompanyResponse
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

    fun addCompany(request: CreateCompanyRequest): CreateCompanyResponse {
        val company = JCompaniesRecord().apply {
            name = request.name
            director = request.director
        }.let(companyRepository::save)

        return CreateCompanyResponse(id = company.id)
    }
    fun deleteCompanyById(id: Int) {
        val msg: String

        if (employeeRepository.checkByCompanyId(id)) {
            msg = "Нельзя удалить компанию, если в ней есть работники!"
            throw FailedCompanyDelete(msg)
        }

        companyRepository.deleteById(id)
    }

    fun deleteAllCompanyEmployees(companyId: Int) =
            employeeRepository.deleteByCompanyId(companyId)
}