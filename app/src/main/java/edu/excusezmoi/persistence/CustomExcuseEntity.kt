package edu.excusezmoi.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customs")
data class CustomExcuseEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "text")
    var text: String
)
