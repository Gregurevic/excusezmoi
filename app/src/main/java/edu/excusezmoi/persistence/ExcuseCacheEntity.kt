package edu.excusezmoi.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "excuses")
data class ExcuseCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "text")
    var text: String
)
