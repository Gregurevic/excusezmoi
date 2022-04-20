package edu.excusezmoi.persistence

import androidx.room.*

@Dao
interface ExcuseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCacheEntity(excuseCacheEntity: ExcuseCacheEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBan(banEntity: BanEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModification(modificationEntity: ModificationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustom(customExcuseEntity: CustomExcuseEntity): Long

    @Query("SELECT * FROM excuses")
    suspend fun getAllCached(): List<ExcuseCacheEntity>

    @Query("SELECT EXISTS (SELECT 1 FROM bans WHERE id = :id)")
    suspend fun isBanned(id: Int): Boolean

    @Query("SELECT * FROM modifications WHERE id = :id ORDER BY id ASC LIMIT 1")
    suspend fun getModified(id: Int): ModificationEntity

    @Query("SELECT * FROM customs WHERE id = :id ORDER BY id ASC LIMIT 1")
    suspend fun getCustom(id: Int): CustomExcuseEntity

    @Query("SELECT id FROM customs")
    suspend fun getCustomIds(): List<Int>

    @Query("SELECT id FROM customs WHERE category = :category")
    suspend fun getCustomIdsByCategory(category: String): List<Int>
}
