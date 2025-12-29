package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class CategoryItem(
    @ColumnInfo(name = Constants.CATEGORY_NAME)
    var name: String,
    @ColumnInfo(name = Constants.CATEGORY_COLOR_CODE)
    var colorCode: String?,
    @ColumnInfo(name = Constants.CATEGORY_ICON)
    var icon: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
