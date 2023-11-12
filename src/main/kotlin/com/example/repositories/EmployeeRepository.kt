package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JEmployeesRecord
import org.jooq.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import com.example.db.schema.Tables.EMPLOYEES
import com.example.db.schema.Tables.POSITIONS
import com.example.model.*

@Repository
class EmployeeRepository : AbstractRepository<JEmployees, JEmployeesRecord>() {


    override var table = EMPLOYEES

    override fun getById(id: Int) =
            findById<JEmployees>(id)

    fun getAll(): List<JEmployees> =
            dsl.selectFrom(table).fetchInto(JEmployees::class.java)

    fun getByCompanyId(id: Int) =
            dsl.select(table.FIO, table.DATE_OF_BIRTHDAY, POSITIONS.NAME)
                    .from(table)
                    .join(POSITIONS)
                    .on(table.POSITION_ID.eq(POSITIONS.ID))
                    .fetchInto(EmployeePosition::class.java)


    fun getByPositionId(id: Int) =
            dsl.select(table.FIO, table.DATE_OF_BIRTHDAY)
                    .from(table)
                    .where(table.ID.eq(id))
                    .fetchInto(EmployeeShort2::class.java)

    fun getAllShort() =
            dsl.select(table.ID, table.FIO)
                    .from(table)
                    .fetchInto(EmployeeShort::class.java)

    fun checkByCompanyId(id: Int) =
            dsl.fetchExists(
                    dsl.selectFrom(table).where(table.COMPANY_ID.eq(id))
            )

    fun checkByPositionId(id: Int) =
            dsl.fetchExists(
                    dsl.selectFrom(table).where(table.POSITION_ID.eq(id))
            )

    override fun save(record: JEmployeesRecord) =
            store<JEmployees>(record)

    override fun deleteById(id: Int) {
        dsl.deleteFrom(table).where(table.ID.eq(id)).execute()
    }

    fun deleteByCompanyId(companyId: Int) =
            dsl.deleteFrom(table).where(table.COMPANY_ID.eq(companyId))
                    .execute()
}