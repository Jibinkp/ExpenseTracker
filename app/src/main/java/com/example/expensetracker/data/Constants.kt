package com.example.expensetracker.data

object Constants {
    const val DATABASE = "expense_tracker.db"

    // Category
    const val CATEGORY_TABLE_NAME = "categories"
    const val CATEGORY_ID = "id"
    const val CATEGORY_TITLE = "title"
    const val CATEGORY_NOTE = "note"
    const val CATEGORY_STATUS = "status"
    const val CATEGORY_ADDED_DATE = "added_date"
    const val CATEGORY_UPDATED_DATE = "updated_date"
    // END OF CATEGORY

    // Transaction
    const val TRANSACTION_TABLE_NAME = "transactions"
    const val TRANSACTION_ID = "id"
    const val TRANSACTION_AMOUNT = "amount"
    const val TRANSACTION_CATEGORY_ID = "category_id"
    const val TRANSACTION_NOTE = "note"
    const val TRANSACTION_DATE = "date"
    const val TRANSACTION_TYPE = "type"
    const val TRANSACTION_TITLE = "title"
    const val TRANSACTION_STATUS = "status"
    const val TRANSACTION_ADDED_DATE = "added_date"
    const val TRANSACTION_UPDATED_DATE = "updated_date"
    const val TRANSACTION_PAYMENT_TYPE = "payment_type"
    // END OF TRANSACTION

    // BUDGET
    const val BUDGET_TABLE_NAME = "budgets"
    const val BUDGET_ID = "id"
    const val BUDGET_AMOUNT = "amount"
    const val BUDGET_NOTE = "note"
    const val BUDGET_START_DATE = "start_date"
    const val BUDGET_END_DATE = "end_date"
    const val BUDGET_STATUS = "status"
    const val BUDGET_ADDED_DATE = "added_date"
    const val BUDGET_UPDATED_DATE = "updated_date"
    // END OF BUDGET

    // GOAL
    const val GOAL_TABLE_NAME = "goals"
    const val GOAL_TITLE = "title"
    const val GOAL_AMOUNT = "amount"
    const val GOAL_CONTRIBUTION_TYPE = "contribution_type"
    const val GOAL_DEAD_LINE = "dead_line"
    const val GOAL_STATUS = "status"
    const val GOAL_ADDED_DATE = "added_date"
    const val GOAL_UPDATED_DATE = "updated_date"
    // END OF GOAL

    // REMINDER
    const val REMINDER_TABLE_NAME = "reminders"
    const val REMINDER_TITLE = "title"
    const val REMINDER_NOTE = "note"
    const val REMINDER_AMOUNT = "amount"
    const val REMINDER_FREQUENCY = "frequency"
    const val REMINDER_DATE = "date"
    const val REMINDER_STATUS = "status"
    const val REMINDER_ADDED_DATE = "added_date"
    const val REMINDER_UPDATED_DATE = "updated_date"
    // END OF REMINDER

    enum class ENUM_STATUS{
        ACTIVE,
        DEACTIVATE,
    }

    enum class ENUM_TRANSACTION_TYPE{
        INCOME,
        EXPENSE
    }

    const val LIST_DEFAULT_LIMIT = 3
    const val DATE_FORMAT_YYYY_MM = "yyyy-MM"
    const val DATE_FORMAT_MMMM_YYYY = "MMMM yyyy"
    const val DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd"
    const val DATE_FORMAT_MMM_DD_YYYY = "MMM dd. yyyy"
}