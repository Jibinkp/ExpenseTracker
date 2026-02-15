package com.example.expensetracker.ui.category

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.databinding.LayoutAddCategoryDialogBinding

class AddCategoryDialog(
    context: Context,
    private val addCategoryDialogListener: AddCategoryDialogListener
) : AppCompatDialog(context) {
    private lateinit var binding: LayoutAddCategoryDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddCategoryDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        binding.txtCancel.setOnClickListener {
            cancel()
        }
        binding.txtAdd.setOnClickListener {
            val name = binding.txtName.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(context, "Enter the category name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val item = CategoryItem(
                name,
                null,
                "ACTIVE",
                "2026-02-15",
                "2026-02-15",
            )
            addCategoryDialogListener.onAddClickListener(item)
            dismiss()
        }
    }
}