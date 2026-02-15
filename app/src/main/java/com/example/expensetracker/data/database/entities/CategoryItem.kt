package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.CATEGORY_TABLE_NAME)
data class CategoryItem(
    @ColumnInfo(name = Constants.CATEGORY_TITLE)
    var categoryTitle: String,
    @ColumnInfo(name = Constants.CATEGORY_NOTE)
    var catgeoryNote: String,
    @ColumnInfo(name = Constants.CATEGORY_STATUS)
    var categoryStatus: String,
    @ColumnInfo(name = Constants.CATEGORY_ADDED_DATE)
    var categoryAddedDate: String,
    @ColumnInfo(name = Constants.CATEGORY_UPDATED_DATE)
    var categoryUpdatedDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
