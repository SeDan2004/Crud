package com.example.controllers

import com.example.model.Companies.CreateCompanyRequest
import com.example.model.Companies.CreateCompanyResponse
import com.example.model.Companies.UpdateDirectorRequest
import com.example.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/companies")
class CompanyController {

    @Autowired
    lateinit var companyService: CompanyService

    @GetMapping("/{company_id}")
    fun getCompanyById(@PathVariable("company_id") companyId: Int) =
            companyService.getByCompanyId(companyId)

    @GetMapping("/director/{company_id}")
    fun getCompanyDirector(@PathVariable("company_id") companyId: Int) =
            ResponseEntity(companyService.getCompanyDirector(companyId), HttpStatus.OK)

    @GetMapping("/employees/{company_id}")
    fun getCompanyEmployees(@PathVariable("company_id") companyId: Int) =
            ResponseEntity(companyService.getEmployeesByCompanyId(companyId), HttpStatus.OK)

    @PostMapping
    fun addCompany(@RequestBody request: CreateCompanyRequest) : ResponseEntity<CreateCompanyResponse> {
        return ResponseEntity(companyService.addCompany(request), HttpStatus.OK)
    }

    @PatchMapping("/director/{company_id}")
    fun updateCompanyDirector(@PathVariable("company_id") companyId: Int,
                              @RequestBody updateDirectorRequest: UpdateDirectorRequest) =
            companyService.updateCompanyDirector(companyId, updateDirectorRequest)

    @DeleteMapping("/{company_id}")
    fun deleteCompany(@PathVariable("company_id") companyId: Int) =
            companyService.deleteCompanyById(companyId)

    @DeleteMapping("/employees/{company_id}")
    fun deleteCompanyEmployees(@PathVariable("company_id") companyId: Int) =
            companyService.deleteAllCompanyEmployees(companyId)
}