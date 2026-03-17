package com.example.expensetracker.ui.expenses

import com.example.expensetracker.data.database.entities.ExpenseItem

interface AddEditExpenseDialogListener {
    fun onSaveExpenseClickListener(expenseItem: ExpenseItem)
}