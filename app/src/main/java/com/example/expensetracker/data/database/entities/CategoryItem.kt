package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants
import java.sql.Timestamp

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class CategoryItem(
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "primary_type")
    val primaryType: String,
    @ColumnInfo(name = "icon_name")
    val iconName: String? = null,
    @ColumnInfo(name = "is_default")
    val isDefault: Int? = null,
    @ColumnInfo(name = "is_archived")
    val isArchived: Int? = null,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun toString(): String {
        return name
    }
}
