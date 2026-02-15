package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.TRANSACTION_TABLE_NAME)
data class TransactionsItem(
    @ColumnInfo(name = Constants.TRANSACTION_AMOUNT)
    var amount: Double,
    @ColumnInfo(name = Constants.TRANSACTION_CATEGORY_ID)
    var categoryId: Int,
    @ColumnInfo(name = Constants.TRANSACTION_NOTE)
    var note: String,
    @ColumnInfo(name = Constants.TRANSACTION_DATE)
    var date: String,
    @ColumnInfo(name = Constants.TRANSACTION_TYPE)
    var type: String,
    @ColumnInfo(name = Constants.TRANSACTION_TITLE)
    var transactionTitle: String,
    @ColumnInfo(name = Constants.TRANSACTION_STATUS)
    var transactionStatus: String,
    @ColumnInfo(name = Constants.TRANSACTION_ADDED_DATE)
    var transactionAddedDate: String,
    @ColumnInfo(name = Constants.TRANSACTION_UPDATED_DATE)
    var transactionUpdatedDate: String,
    @ColumnInfo(name = Constants.TRANSACTION_PAYMENT_TYPE)
    var transactionPaymentType: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
