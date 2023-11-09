package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JEmployeesRecord
import com.example.exceptions.ForeignKeyNotFound
import com.example.model.CompanyEmployeesShort
import com.example.model.EmployeeShort
import org.jooq.*
import org.jooq.impl.DSL.count
import org.jooq.impl.DSL.exists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class EmployeeRepository {

    val table = Tables.EMPLOYEES

    @Autowired
    private lateinit var dsl: DSLContext

    fun get(id: Int) =
            dsl.selectFrom(table).where(table.ID.eq(id)).fetchInto(JEmployees::class.java)
    fun getAll(): List<JEmployees> =
            dsl.selectFrom(table).fetchInto(JEmployees::class.java)

    fun getByCompanyId(id: Int) : List<CompanyEmployeesShort> {
        val positions = Tables.POSITIONS

        return dsl.select(table.FIO, table.DATE_OF_BIRTHDAY, positions.POSITION)
                .from(table)
                .join(positions)
                .on(table.POSITION_ID.eq(positions.ID))
                .fetchInto(CompanyEmployeesShort::class.java)
    }

    fun getAllShort() =
            dsl.select(table.ID, table.FIO).from(table).fetchInto(EmployeeShort::class.java)
    fun create(record: JEmployeesRecord) {
        val isKeyExist: Boolean
        val company = Tables.COMPANY
        val positions = Tables.POSITIONS

        fun String.toBoolean() = equals("1")

        isKeyExist = dsl.select(count()).from(company)
                                        .where(company.ID.eq(record.companyId))
                                        .and(
                                            exists(
                                                dsl.select(count()).from(positions)
                                                                   .where(positions.ID.eq(record.positionId))
                                            )
                                        ).fetchOne(0, Int::class.java)
                                         .toString()
                                         .toBoolean()

        if (!isKeyExist) {
            val msg = "Один из внешних ключей (company_fkey, positions_fkey) " +
                      "отсутствует в базе данных."

            throw ForeignKeyNotFound(msg)
        } else {
            dsl.newRecord(table, record).let {
                it.insert()
                it.refresh()
                it.into(JEmployees::class.java)
            }
        }
    }
    fun delete(id: Int) =
            dsl.deleteFrom(table).where(table.ID.eq(id)).execute()

    fun deleteAllCompanyEmployees(companyId: Int) =
            dsl.deleteFrom(table).where(table.COMPANY_ID.eq(companyId))
                                 .execute()
}