package com.example.expensetracker.ui.utils.dialogutils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.expensetracker.R

class DialogUtils(val context: Context) {

    fun showDialog(
        title: String?,
        message: String?,
        dialogClickListener: DialogClickListener,
        item: Any?
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setIcon(R.drawable.ic_income)
            .setPositiveButton("Accept") { _, _ ->
                dialogClickListener.onClickAcceptListener(item)
            }
            .setNegativeButton("Decline") { _, _ ->
                dialogClickListener.onClickDeclineListener()
            }
            .show()
    }
}