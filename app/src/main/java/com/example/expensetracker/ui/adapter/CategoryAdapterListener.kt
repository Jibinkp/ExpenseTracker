package com.example.expensetracker.ui.adapter

import com.example.expensetracker.data.database.entities.CategoryItem

interface CategoryAdapterListener {
    fun deleteClickListener(item: CategoryItem)
}