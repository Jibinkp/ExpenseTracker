package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "expense")
data class ExpenseItem(
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "payment_type")
    val paymentType: String,
    @ColumnInfo(name = "note")
    val note: String?,
    @ColumnInfo(name = "server_id")
    val serverId: String?,
    @ColumnInfo(name = "is_synced")
    val isSynced: Int?,
    @ColumnInfo(name = "is_archived")
    val isArchived: Int?,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "updated_at")
    val updatedAt: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
