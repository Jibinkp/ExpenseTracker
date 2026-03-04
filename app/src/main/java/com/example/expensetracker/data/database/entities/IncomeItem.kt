package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "income")
data class IncomeItem(
    @ColumnInfo(name = "amount")
    val amount: Double,
    @ColumnInfo(name = "source")
    val source: String,
    @ColumnInfo(name = "date")
    val date: String,
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
