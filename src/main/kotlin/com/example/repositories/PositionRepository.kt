package com.example.repositories

import com.example.db.schema.Tables.EMPLOYEES
import com.example.db.schema.Tables.POSITIONS
import com.example.db.schema.tables.JPositions
import com.example.db.schema.tables.pojos.JEmployees
import com.example.db.schema.tables.records.JPositionsRecord
import com.example.model.CreatePositionResponse
import com.example.model.EmployeeShort2
import org.jooq.DSLContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PositionRepository : AbstractRepository<JPositions, JPositionsRecord>() {

    override val table = POSITIONS

    override fun getById(id: Int) =
            findById<JPositions>(id)

    fun isExistsById(id: Int) =
            dsl.fetchExists(table, table.ID.eq(id))

    override fun save(record: JPositionsRecord) =
            store<JPositions>(record)

    override fun deleteById(id: Int) {
        dsl.deleteFrom(table).where(table.ID.eq(id))
                .execute()
    }
}