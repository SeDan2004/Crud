package com.example.repositories

import com.example.db.schema.Tables
import com.example.model.EmployeeShort2
import org.jooq.DSLContext
import org.jooq.tools.reflect.Reflect.on
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class PositionRepository {

    var table = Tables.POSITIONS

    @Autowired
    lateinit var dsl: DSLContext

    fun getEmployees(positionId: Int) : List<EmployeeShort2> {
        var employees = Tables.EMPLOYEES

        return dsl.select(employees.FIO, employees.DATE_OF_BIRTHDAY)
                  .from(table)
                  .join(employees)
                  .on(table.ID.eq(employees.POSITION_ID))
                  .where(employees.POSITION_ID.eq(positionId))
                  .fetchInto(EmployeeShort2::class.java)
    }

    fun add(position: String) =
            dsl.insertInto(table).columns(table.POSITION)
                                 .values(position)
                                 .execute()

    fun delete(positionId: Int) {
        var employees = Tables.EMPLOYEES

        dsl.deleteFrom(table).where(table.ID.eq(positionId))
                             .execute()

        dsl.update(employees).set(employees.POSITION_ID, 1)
                             .where(employees.POSITION_ID.eq(positionId))
                             .execute()
    }
}