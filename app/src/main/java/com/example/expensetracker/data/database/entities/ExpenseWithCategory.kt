package com.example.expensetracker.data.database.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseWithCategory(
    @Embedded
    val expense: ExpenseItem,

    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryItem
)
