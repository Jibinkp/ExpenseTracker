package com.example.expensetracker.ui.income

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import com.example.expensetracker.R
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.databinding.LayoutAddEditExpenseBinding
import com.example.expensetracker.databinding.LayoutAddEditIncomeBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils
import com.example.expensetracker.ui.utils.dateutils.DateUtilsClickListener

class AddEditIncomeDialog(
    context: Context,
    private val addEditIncomeDialogListener: AddEditIncomeDialogListener
) : Dialog(context) {
    private lateinit var binding: LayoutAddEditIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddEditIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        window?.setLayout(
            (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        initView()
    }

    private fun initView() {
        binding.btnSave.setOnClickListener {
            val amount = binding.etAmount.text.toString()
            if (amount.isEmpty()) {
                binding.etAmount.error = ContextCompat.getString(context,R.string.enter_the_amount)
                return@setOnClickListener
            }

            val source = binding.etSource.text.toString()
            if (source.isEmpty()) {
                binding.etSource.error = ContextCompat.getString(context,R.string.enter_the_source)
                return@setOnClickListener
            }

            val date = binding.etDate.text.toString()
            if (date.isEmpty()) {
                binding.etDate.error = ContextCompat.getString(context,R.string.select_date)
                return@setOnClickListener
            }

            val incomeItem = IncomeItem(
                amount.toDouble(),
                source,
                date
            )
            addEditIncomeDialogListener.onSaveClickListener(incomeItem)
            dismiss()
        }

        binding.etDate.setOnClickListener {
            DateUtils.showDatePickerBy(context, null, null, 1, object : DateUtilsClickListener {
                override fun onSuccess(
                    outPutId: Int,
                    key: String,
                    date: String
                ) {
                    if (outPutId == 1) {
                        binding.etDate.setText(date)
                    }
                }

            })
        }
    }
}