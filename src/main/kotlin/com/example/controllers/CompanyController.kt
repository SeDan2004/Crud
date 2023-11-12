package com.example.controllers

import com.example.model.CreateCompanyRequest
import com.example.model.CreateCompanyResponse
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

    @PostMapping("/add")
    fun addCompany(@RequestBody request: CreateCompanyRequest) : ResponseEntity<CreateCompanyResponse> {
        return ResponseEntity(companyService.addCompany(request), HttpStatus.OK)
    }

    @PutMapping("/director/{company_id}")
    fun updateCompanyDirector(@PathVariable("company_id") companyId: Int,
                              @RequestParam("director") director: String) =
            companyService.updateCompanyDirector(companyId, director)

    @DeleteMapping("/{company_id}")
    fun deleteCompany(@PathVariable("company_id") companyId: Int) =
            companyService.deleteCompanyById(companyId)

    @DeleteMapping("/employees/{company_id}")
    fun deleteCompanyEmployees(@PathVariable("company_id") companyId: Int) =
            companyService.deleteAllCompanyEmployees(companyId)
}