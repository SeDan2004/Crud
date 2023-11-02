package com.example.controllers

import com.example.model.CreateCompanyRequest
import com.example.services.CompanyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CompanyController {

    @Autowired
    lateinit var companyService: CompanyService

    @GetMapping("/get_company")
    fun getCompanyById(@RequestParam("id") companyId: Int) =
            companyService.getCompanyById(companyId)

    @GetMapping("/get_director")
    fun getCompanyDirector(@RequestParam("id") companyId: Int) =
            companyService.getCompanyDirector(companyId)

    @GetMapping("/get_company_employees")
    fun getCompanyEmployees(@RequestParam("id") companyId: Int) =
            companyService.getCompanyEmployee(companyId)

    @PostMapping("/add_company")
    fun addCompany(@RequestBody request: CreateCompanyRequest) =
            ResponseEntity(companyService.addCompany(request), HttpStatus.OK)

    @PostMapping("/update_director")
    fun updateCompanyDirector(@RequestParam("id") companyId: Int,
                              @RequestParam("director") director: String) =
            companyService.updateCompanyDirector(companyId, director)

    @PostMapping("/delete_company")
    fun deleteCompany(@RequestParam("id") companyId: Int) =
            companyService.deleteCompany(companyId)
}