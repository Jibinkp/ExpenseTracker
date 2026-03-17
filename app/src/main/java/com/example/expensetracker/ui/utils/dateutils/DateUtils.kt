package com.example.expensetracker.ui.utils.dateutils

import android.app.DatePickerDialog
import android.content.Context
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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

    fun convertDateToOtherFormat(
        dateStr: String?,
        inputPattern: String,
        outputPattern: String
    ): String {

        if (!dateStr.isNullOrEmpty()) {
            val inFormat = SimpleDateFormat(inputPattern, Locale.getDefault())

            val date: Date? = try {
                inFormat.parse(dateStr)
            } catch (e: ParseException) {
                e.printStackTrace()
                null
            }

            val outFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

            return if (date != null) {
                outFormat.format(date)
            } else {
                ""
            }
        }

        return ""
    }

    fun getCurrentDateAndTime(format: String?): String? {
        val c = Calendar.getInstance().time
        val df = SimpleDateFormat(format)
        return df.format(c)
    }

    fun getStringToDate(date: String,format: String) {
        val format = SimpleDateFormat(format)
        val date = format.parse(date)
    }
}