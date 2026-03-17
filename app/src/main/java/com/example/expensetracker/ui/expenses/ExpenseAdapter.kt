package com.example.expensetracker.ui.expenses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.Constants.DATE_FORMAT_MMM_DD_YYYY
import com.example.expensetracker.data.Constants.DATE_FORMAT_YYYY_MM_DD
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.entities.ExpenseWithCategory
import com.example.expensetracker.databinding.LayoutExpenseItemBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils

class ExpenseAdapter(var items: List<ExpenseWithCategory>) :
    RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseViewHolder {
        val binding =
            LayoutExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ExpenseViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ExpenseViewHolder(private val binding: LayoutExpenseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseWithCategory) {
            binding.tvDate.text =
                DateUtils.convertDateToOtherFormat(item.expense.date, DATE_FORMAT_YYYY_MM_DD, DATE_FORMAT_MMM_DD_YYYY)
            val amount = "-₹${item.expense.amount}"
            binding.tvAmount.text = amount
            binding.tvTitle.text = item.category.name
            when (item.category.primaryType) {

                PrimaryTypes.NEEDS -> {
                    binding.tvExpenseLabel.text = "N"
                    binding.tvExpenseLabel.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                    binding.linearLayout.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.expense_background
                    )
                }

                PrimaryTypes.WANTS -> {
                    binding.tvExpenseLabel.text = "W"
                    binding.tvExpenseLabel.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.orange
                        )
                    )
                    binding.linearLayout.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.wants_background
                    )
                }

                PrimaryTypes.SAVINGS -> {
                    binding.tvExpenseLabel.text = "S"
                    binding.tvExpenseLabel.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.purple_200
                        )
                    )
                    binding.linearLayout.background = ContextCompat.getDrawable(
                        binding.root.context,
                        R.drawable.savings_background
                    )

                }
            }
        }

    }

}