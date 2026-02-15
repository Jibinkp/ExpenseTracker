package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.REMINDER_TABLE_NAME)
data class ReminderItem(
    @ColumnInfo(name = Constants.REMINDER_TITLE)
    var reminderTitle: String,
    @ColumnInfo(name = Constants.REMINDER_NOTE)
    var reminderNote: String,
    @ColumnInfo(name = Constants.REMINDER_AMOUNT)
    var reminderAmount: Double,
    @ColumnInfo(name = Constants.REMINDER_FREQUENCY)
    var reminderFrequency: String,
    @ColumnInfo(name = Constants.REMINDER_DATE)
    var reminderDate: String,
    @ColumnInfo(name = Constants.REMINDER_STATUS)
    var reminderStatus: String,
    @ColumnInfo(name = Constants.REMINDER_ADDED_DATE)
    var reminderAddedDate: String,
    @ColumnInfo(name = Constants.REMINDER_UPDATED_DATE)
    var reminderUpdatedDate: String,
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
