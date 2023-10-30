package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.model.EmployeeShort
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Result
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.jooq.Table
import org.jooq.impl.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.sql.Connection
import java.sql.DriverManager
import kotlin.reflect.KVisibility
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Public

@Repository
class UserRepository {

    val table = Tables.EMPLOYEES

    @Autowired
    private lateinit var dsl: DSLContext

    fun getUserById(id: Int) {

    }

    fun findAllShort() =
            dsl.select(table.ID, table.FIO).from(table).fetchInto(EmployeeShort::class.java)

    fun create(record: JEmployeesRecord) =
            dsl.newRecord(table, record).let {
                it.insert()
                it.refresh()
                it.into(JEmployees::class.java)
            }

    fun findAll(): List<JEmployees> =
            dsl.selectFrom(table).fetchInto(JEmployees::class.java)

    fun delete(id: Int) {

    }

    fun update(id: Int, column: String, columnVal: String) {

    }
}