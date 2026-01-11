package com.example.expensetracker.ui.transaction

import com.example.expensetracker.data.database.entities.TransactionsItem

interface AddTransactionDialogListener {
    fun onAddClickListener(item: TransactionsItem)
}