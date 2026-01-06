package com.example.expensetracker.ui.transaction

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.example.expensetracker.databinding.LayoutAddTransactionDialogBinding

class AddTransactionDialog(context: Context) : AppCompatDialog(context) {
    private lateinit var binding: LayoutAddTransactionDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddTransactionDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {

    }
}