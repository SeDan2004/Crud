package com.example.repositories

import com.example.db.schema.Tables
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.Table
import org.jooq.UpdatableRecord
import org.jooq.impl.TableImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository
open abstract class AbstractRepository<T : Serializable, S : UpdatableRecord<S>, R> {

    abstract val table: Table<S>

    abstract fun getById(id: Int) : MutableList<T>
    abstract fun insert(record: S) : R
    abstract fun deleteById(id: Int)
}