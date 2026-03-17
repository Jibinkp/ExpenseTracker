package com.example.expensetracker.data

object PaymentMethodType {
    const val CASH = "CASH"
    const val CARD = "CARD"
    const val UPI = "UPI"
    const val OTHER = "OTHER"

    fun getAll(): List<String> {
        return listOf(CASH, CARD, UPI, OTHER)
    }
}