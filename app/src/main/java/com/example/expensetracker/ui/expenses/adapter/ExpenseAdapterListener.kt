package com.example.expensetracker.ui.expenses.adapter

import com.example.expensetracker.data.database.entities.ExpenseWithCategory

interface ExpenseAdapterListener {
    fun onDeleteClickListener(item:ExpenseWithCategory)
}