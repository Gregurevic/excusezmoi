package edu.excusezmoi.persistence

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExcuseCacheEntity::class], version = 1)
abstract class ExcuseDatabase : RoomDatabase() {

    abstract fun excuseDao(): ExcuseDao

    companion object {
        val DATABASE_NAME: String = "excuse_db"
    }
}