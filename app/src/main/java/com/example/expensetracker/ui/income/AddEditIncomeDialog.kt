package com.example.expensetracker.ui.income

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import com.example.expensetracker.databinding.LayoutAddEditExpenseBinding
import com.example.expensetracker.databinding.LayoutAddEditIncomeBinding

class AddEditIncomeDialog(context: Context) : Dialog(context) {
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

    }
}