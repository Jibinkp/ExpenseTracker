package com.example.expensetracker.ui.utils.dateutils

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar
import java.util.Date

object DateUtils {

    fun showDatePickerBy(
        context: Context,
        startDate: Date?,
        endDate: Date?,
        outPutId: Int,
        dateUtilsClickListener: DateUtilsClickListener
    ) {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            context,
            { view, year, monthOfYear, dayOfMonth ->
                var monthOfYear = monthOfYear
                monthOfYear += 1
                val month = if (monthOfYear > 9) monthOfYear.toString() else "0$monthOfYear"
                val day = if (dayOfMonth > 9) dayOfMonth.toString() else "0$dayOfMonth"
                dateUtilsClickListener.onSuccess(outPutId, "datePicker", "$year-$month-$day")
            }, mYear, mMonth, mDay
        )
        if (startDate != null) {
            datePickerDialog.datePicker.minDate = startDate.time
        }
        if (endDate != null) {
            datePickerDialog.datePicker.maxDate = endDate.time
        }
        datePickerDialog.show()
    }
}