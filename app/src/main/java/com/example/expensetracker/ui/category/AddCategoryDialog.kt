package com.example.expensetracker.ui.category

import android.R
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import androidx.core.graphics.drawable.toDrawable
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.databinding.LayoutAddCategoryDialogBinding

class AddCategoryDialog(
    context: Context,
    private val addCategoryDialogListener: AddCategoryDialogListener
) : Dialog(context) {
    private lateinit var binding: LayoutAddCategoryDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutAddCategoryDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
        window?.setLayout(
            (context.resources.displayMetrics.widthPixels * 0.9).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        initView()
    }

    private fun initView() {
        val adapter = ArrayAdapter(
            context,
            R.layout.simple_list_item_1,
            PrimaryTypes.getAll()
        )
        binding.etPrimaryType.setAdapter(adapter)


        binding.btnSaveCategory.setOnClickListener {
            val name = binding.etName.text.toString()
            if (name.isEmpty()) {
                binding.etName.error = "Enter category name"
                return@setOnClickListener
            }
            val primaryType = binding.etPrimaryType.text.toString()
            if (primaryType.isEmpty()) {
                binding.etName.error = "Select category type"
                return@setOnClickListener
            }

            val categoryItem = CategoryItem(
                name,
                primaryType
            )

            addCategoryDialogListener.onAddClickListener(categoryItem)
            dismiss()
        }

        binding.imgClose.setOnClickListener {
            dismiss()
        }
    }
}