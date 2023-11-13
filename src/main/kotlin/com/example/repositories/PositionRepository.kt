package com.example.repositories

import com.example.db.schema.Tables.POSITIONS
import com.example.db.schema.tables.JPositions
import com.example.db.schema.tables.records.JPositionsRecord
import org.springframework.stereotype.Repository

@Repository
class PositionRepository : AbstractRepository<JPositions, JPositionsRecord>() {

    override val table = POSITIONS

    override fun getById(id: Int) =
            findById<JPositions>(id)

    override fun save(record: JPositionsRecord) =
            store<JPositions>(record)
}