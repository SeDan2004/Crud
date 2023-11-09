package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JCompany
import com.example.db.schema.tables.records.JCompanyRecord
import com.example.exceptions.DeleteAtFirstEmployees
import com.example.model.CompanyDirector
import com.example.model.CreateCompanyResponse
import com.example.model.EmployeeShort2
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CompanyRepository {

    @Autowired
    lateinit var dsl: DSLContext

    var table = Tables.COMPANY

    fun getById(id: Int) =
            dsl.selectFrom(table).where(table.ID.eq(id)).fetchInto(JCompany::class.java)

    fun getDirector(id: Int) =
            dsl.select(table.DIRECTOR).from(table)
                                      .where(table.ID.eq(id))
                                      .fetchInto(CompanyDirector::class.java)

    fun updateDirector(id: Int, director: String) =
            dsl.update(table).set(row(table.DIRECTOR), row(director))
                             .where(table.ID.eq(id))
                             .execute()

    fun add(record: JCompanyRecord) =
        dsl.newRecord(table, record).let {
            it.insert()
            it.refresh()
            it.into(CreateCompanyResponse::class.java)
        }

    fun delete(id: Int) {
        var employees = Tables.EMPLOYEES
        var employeesCount = dsl.select(count())
                                .from(employees)
                                .where(employees.COMPANY_ID.eq(id))
                                .fetchOne(0, Int::class.java)

        println(employeesCount)

        if (employeesCount != 0) {
            val msg = "Прежде чем удалить компанию, сперва удалите её сотрудников!"
            throw DeleteAtFirstEmployees(msg)
        } else {
            dsl.deleteFrom(table).where(table.ID.eq(id))
                                 .execute()
        }
    }
}