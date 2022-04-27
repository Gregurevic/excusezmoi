package edu.excusezmoi.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ExcuseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExcuses(excuses: List<ExcuseCacheEntity>)

    @Query("SELECT * FROM excuses")
    suspend fun selectExcuses(): List<ExcuseCacheEntity>

    @Query("DELETE FROM excuses")
    suspend fun nukeExcuses()
}
