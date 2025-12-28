package com.example.expensetracker.data

object Constants {
    const val DATABASE = "expense_tracker.db"

    // Category
    const val CATEGORY_TABLE_NAME = "categories"
    const val CATEGORY_NAME = "name"
    const val CATEGORY_COLOR_CODE = "color_code"
    const val CATEGORY_ICON = "icon"

    // Transaction
    const val TRANSACTION_TABLE_NAME = "transactions"
    const val TRANSACTION_AMOUNT = "amount"
    const val TRANSACTION_CATEGORY_ID = "category_id"
    const val TRANSACTION_NOTE = "note"
    const val TRANSACTION_DATE = "date"
    const val TRANSACTION_TYPE = "type"
}