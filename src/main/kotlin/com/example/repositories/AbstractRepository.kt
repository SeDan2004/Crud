package com.example.repositories

import com.example.db.schema.Tables
import com.example.db.schema.tables.pojos.JEmployees
import com.example.model.CreatePositionResponse
import org.jooq.*
import org.jooq.impl.TableImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.io.Serializable

@Repository
open abstract class AbstractRepository<T : Serializable, S : UpdatableRecord<S>> {

    @Autowired
    lateinit var dsl: DSLContext

    abstract val table: Table<S>

    val TABLE_ID: Field<Int> by lazy {table.field("id", Int::class.java)!!}

    final inline fun <reified T> findById(id: Int): T? =
            dsl.selectFrom(table)
                    .where(TABLE_ID.eq(id))
                    .fetchOneInto(T::class.java)

    abstract fun getById(id: Int) : T?
    abstract fun save(r: S): T

    final inline fun <reified T> store(record: S): T =
            dsl.newRecord(table, record).let {
                if (record[TABLE_ID] != null) {
                    it.update()
                } else {
                    it.insert()
                }

                it.refresh()
                it.into(T::class.java)
            }

    abstract fun deleteById(id: Int)
}