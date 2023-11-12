package com.example.repositories

import com.example.db.schema.Tables.EMPLOYEES
import com.example.db.schema.Tables.POSITIONS
import com.example.db.schema.tables.JPositions
import com.example.db.schema.tables.records.JPositionsRecord
import com.example.model.CreatePositionResponse
import com.example.model.EmployeeShort2
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PositionRepository : AbstractRepository<JPositions, JPositionsRecord, CreatePositionResponse>() {

    @Autowired
    lateinit var dsl: DSLContext

    override val table = POSITIONS

    override fun getById(id: Int) =
            dsl.selectFrom(table).where(table.ID.eq(id))
                                 .fetchInto(JPositions::class.java)

    fun checkById(id: Int) =
            dsl.fetchExists(
                dsl.selectFrom(table).where(table.ID.eq(id))
            )

    override fun insert(record: JPositionsRecord) =
            dsl.newRecord(table, record).let {
                it.insert()
                it.refresh()
                it.into(CreatePositionResponse::class.java)
            }

    override fun deleteById(id: Int) {
        dsl.deleteFrom(table).where(table.ID.eq(id))
                             .execute()
    }
}