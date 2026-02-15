package com.example.expensetracker.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensetracker.data.Constants

@Entity(tableName = Constants.GOAL_TABLE_NAME)
data class GoalItem(
    @ColumnInfo(name = Constants.GOAL_TITLE)
    var goalTitle: String,
    @ColumnInfo(name = Constants.GOAL_AMOUNT)
    var goalAmount: Double,
    @ColumnInfo(name = Constants.GOAL_CONTRIBUTION_TYPE)
    var goalContributionType: String,
    @ColumnInfo(name = Constants.GOAL_DEAD_LINE)
    var goalDeadLine: String,
    @ColumnInfo(name = Constants.GOAL_STATUS)
    var goalStatus: String,
    @ColumnInfo(name = Constants.GOAL_ADDED_DATE)
    var goalAddedDate: String,
    @ColumnInfo(name = Constants.GOAL_UPDATED_DATE)
    var goalUpdatedDate: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
