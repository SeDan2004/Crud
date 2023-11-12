package com.example.repositories

import com.example.db.schema.Tables.COMPANIES
import com.example.db.schema.tables.pojos.JCompanies
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JCompaniesRecord
import com.example.model.CompanyDirectorResponse
import com.example.model.CreateCompanyResponse
import org.jooq.DSLContext
import org.jooq.impl.DSL.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class CompanyRepository : AbstractRepository<JCompanies, JCompaniesRecord>() {

    override val table = COMPANIES

    override fun getById(id: Int) =
            findById<JCompanies>(id)

    fun getDirector(id: Int) =
            dsl.select(table.DIRECTOR).from(table)
                    .where(table.ID.eq(id))
                    .fetchInto(CompanyDirectorResponse::class.java)

    fun isExistsById(id: Int) =
            dsl.fetchExists(table, table.ID.eq(id))

    override fun save(record: JCompaniesRecord) =
            store<JCompanies>(record)

    fun updateDirector(id: Int, director: String) =
            dsl.update(table).set(row(table.DIRECTOR), row(director))
                    .where(table.ID.eq(id))
                    .execute()

    override fun deleteById(id: Int) {
        dsl.deleteFrom(table).where(table.ID.eq(id))
                .execute()
    }
}