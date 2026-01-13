package com.example.expensetracker.ui.transaction

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.expensetracker.data.database.entities.TransactionsItem
import com.example.expensetracker.databinding.LayoutAddTransactionDialogBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils
import com.example.expensetracker.ui.utils.dateutils.DateUtilsClickListener

class AddTransactionDialog(
    context: Context,
    private val addTransactionDialogListener: AddTransactionDialogListener
) : Dialog(context) {
    private lateinit var binding: LayoutAddTransactionDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddTransactionDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setLayout(
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
            android.view.ViewGroup.LayoutParams.WRAP_CONTENT
        )
        initView()
    }

    private fun initView() {
        binding.tvCancel.setOnClickListener {
            cancel()
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

        binding.tvAdd.setOnClickListener {
            val amount = binding.etAmount.text.toString()
            if (amount.isEmpty()) {
                binding.etAmount.error = "Please enter the amount"
                return@setOnClickListener
            }

            val date = binding.etDate.text.toString()
            if (date.isEmpty()) {
                binding.etDate.error = "Please select date"
                return@setOnClickListener
            }

            val category = binding.etCategory.text.toString()
            if (category.isEmpty()) {
                binding.etCategory.error = "Please select category"
                return@setOnClickListener
            }

            val type = binding.etType.text.toString()
            if (type.isEmpty()) {
                binding.etType.error = "Please select type"
                return@setOnClickListener
            }

            val note = binding.etNote.text.toString()
            val transactionItem = TransactionsItem(
                amount.toDouble(), 1, note, date, type
            )
            addTransactionDialogListener.onAddClickListener(transactionItem)
            dismiss()
        }
    }
}