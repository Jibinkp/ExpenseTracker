package com.example.expensetracker.ui.expenses

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import com.example.expensetracker.databinding.LayoutAddEditExpenseBinding
import androidx.core.graphics.drawable.toDrawable

class AddEditExpenseDialog(context: Context) : Dialog(context) {
    private lateinit var binding: LayoutAddEditExpenseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddEditExpenseBinding.inflate(layoutInflater)
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