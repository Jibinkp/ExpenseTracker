package com.example.expensetracker.ui.expenses



import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.lifecycle.LifecycleOwner
import com.example.expensetracker.R
import com.example.expensetracker.data.PaymentMethodType
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.databinding.LayoutAddEditExpenseBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils
import com.example.expensetracker.ui.utils.dateutils.DateUtilsClickListener

class AddEditExpenseDialog(
    context: Context,
    private val viewModel: ExpenseViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val addEditExpenseDialogListener: AddEditExpenseDialogListener
) : Dialog(context) {
    private lateinit var binding: LayoutAddEditExpenseBinding
    private var category: CategoryItem? = null

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
        val categoryAdapter = ArrayAdapter<CategoryItem>(
            context,
            android.R.layout.simple_list_item_1,
            mutableListOf()
        )
        viewModel.getCategories().observe(lifecycleOwner) {
            categoryAdapter.clear()
            categoryAdapter.addAll(it)
            categoryAdapter.notifyDataSetChanged()
        }
        binding.etCategory.setAdapter(categoryAdapter)
        binding.etCategory.setOnItemClickListener { parent, view, position, id ->
            category = parent.getItemAtPosition(position) as CategoryItem
        }

        val paymentMethodAdapter = ArrayAdapter(
            context, android.R.layout.simple_list_item_1,
            PaymentMethodType.getAll()
        )
        binding.etPaymentMethod.setAdapter(paymentMethodAdapter)

        binding.etDate.setOnClickListener {
            DateUtils.showDatePickerBy(
                context,
                null,
                null,
                1,
                object : DateUtilsClickListener {
                    override fun onSuccess(
                        outPutId: Int,
                        key: String,
                        date: String
                    ) {
                        if (outPutId == 1) {
                            binding.etDate.setText(date)
                        }
                    }

                }
            )
        }

        binding.btnSaveExpense.setOnClickListener {
            val amount = binding.etAmount.text.toString()
            if (amount.isEmpty()) {
                binding.etAmount.error = ContextCompat.getString(context,R.string.enter_the_amount)
                return@setOnClickListener
            }

            if (category == null) {
                binding.etCategory.error = ContextCompat.getString(context,R.string.select_category)
                return@setOnClickListener
            }

            val date = binding.etDate.text.toString()
            if (date.isEmpty()) {
                binding.etDate.error = ContextCompat.getString(context,R.string.select_date)
                return@setOnClickListener
            }

            val paymentMethod = binding.etPaymentMethod.text.toString()
            if (paymentMethod.isEmpty()) {
                binding.etPaymentMethod.error = ContextCompat.getString(context,R.string.select_payment_method)
                return@setOnClickListener
            }

            val note = binding.etNote.text.toString()
            val expenseItem = ExpenseItem(
                amount.toDouble(),
                category?.id!!,
                date,
                paymentMethod,
                note,
            )
            addEditExpenseDialogListener.onSaveExpenseClickListener(expenseItem)
            dismiss()
        }

        binding.imgClose.setOnClickListener {
            dismiss()
        }

    }
}