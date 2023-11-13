package com.example.repositories

import org.jooq.*
import org.jooq.impl.DSL.count
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

    fun getCountAll() =
            dsl.selectCount().from(table)
                             .fetchOne(0, Int::class.java)!!

    fun isExistsById(id: Int) =
            dsl.fetchExists(table, TABLE_ID.eq(id))

    fun deleteById(id: Int) =
            dsl.deleteFrom(table).where(TABLE_ID.eq(id))
                                 .execute()
}