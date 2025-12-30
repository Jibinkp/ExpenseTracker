package com.example.expensetracker.ui.category

import com.example.expensetracker.data.database.entities.CategoryItem

interface AddCategoryDialogListener {

    fun onAddClickListener(item: CategoryItem)
}