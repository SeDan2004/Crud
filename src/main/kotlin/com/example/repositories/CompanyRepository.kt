package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JCompany
import com.example.db.schema.tables.records.JCompanyRecord
import com.example.model.CompanyDirector
import com.example.model.CompanyIdAfterInsert
import com.example.model.CreateCompanyRequest
import com.example.model.EmployeeShort2
import org.jooq.DSLContext
import org.jooq.Table
import org.jooq.impl.DSL.row
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

    /*fun add(record: JCompanyRecord) =
        dsl.newRecord(table, record).let {
            it.insert()
            it.refresh()
            it.into(JCompany::class.java)
        }*/

    /*fun add(record: JCompanyRecord) =
        dsl.insertInto(table).columns(table.NAME, table.DIRECTOR)
                             .values(record.name.toString(), record.director.toString())
                             .returningResult(table.ID)
                             .fetchInto(CompanyIdAfterInsert::class.java)*/

    fun add(name: String, director: String) =
        dsl.insertInto(table).columns(table.NAME, table.DIRECTOR)
                             .values(name, director)
                             .returningResult(table.ID)
                             .fetchInto(CompanyIdAfterInsert::class.java)

    fun getEmployees(id: Int) : MutableList<EmployeeShort2> {
        var employeeTable = Tables.EMPLOYEES

        return dsl.select(employeeTable.FIO,  employeeTable.DATE_OF_BIRTHDAY)
                  .from(table)
                  .join(employeeTable)
                  .on(table.ID.eq(employeeTable.COMPANY_ID))
                  .where(table.ID.eq(id))
                  .fetchInto(EmployeeShort2::class.java)
    }

    fun delete(id: Int) {
        var employeeTable = Tables.EMPLOYEES
        dsl.deleteFrom(table).where(table.ID.eq(id)).execute()
        dsl.deleteFrom(employeeTable).where(employeeTable.COMPANY_ID.eq(id))
                                     .execute()
    }
}