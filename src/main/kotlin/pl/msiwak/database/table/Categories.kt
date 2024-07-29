package pl.msiwak.database.table

import org.jetbrains.exposed.sql.Table

object Categories : Table() {
    val id = varchar("id", 128)
    val userId = varchar("userId", 128)
    val name = varchar("name", 128)
    val exerciseType = varchar("exerciseType", 128)

    override val primaryKey = PrimaryKey(id)
}