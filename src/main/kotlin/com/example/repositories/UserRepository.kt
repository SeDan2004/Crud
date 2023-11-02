package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.model.EmployeeShort
import org.jooq.*
import org.jooq.impl.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class UserRepository {

    val table = Tables.EMPLOYEES

    @Autowired
    private lateinit var dsl: DSLContext

    fun get(id: Int) =
            dsl.selectFrom(table).where(table.ID.eq(id)).fetchInto(JEmployees::class.java)
    fun getAll(): List<JEmployees> =
            dsl.selectFrom(table).fetchInto(JEmployees::class.java)
    fun getAllShort() =
            dsl.select(table.ID, table.FIO).from(table).fetchInto(EmployeeShort::class.java)
    fun create(record: JEmployeesRecord) =
            dsl.newRecord(table, record).let {
                it.insert()
                it.refresh()
                it.into(JEmployees::class.java)
            }
    fun delete(id: Int) =
            dsl.deleteFrom(table).where(table.ID.eq(id)).execute()
}