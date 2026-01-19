package com.example.expensetracker.ui.adapter.category

import com.example.expensetracker.data.database.entities.CategoryItem

interface CategoryAdapterListener {
    fun deleteClickListener(item: CategoryItem)
}