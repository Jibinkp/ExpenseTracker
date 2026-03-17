package com.example.expensetracker.ui.income

import com.example.expensetracker.data.database.entities.IncomeItem

interface AddEditIncomeDialogListener {
    fun onSaveClickListener(incomeItem: IncomeItem)
}