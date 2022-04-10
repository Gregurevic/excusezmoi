package edu.excusezmoi.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExcuseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(excuseCacheEntity: ExcuseCacheEntity): Long

    @Query("SELECT * FROM excuses")
    suspend fun getAll(): List<ExcuseCacheEntity>
}
