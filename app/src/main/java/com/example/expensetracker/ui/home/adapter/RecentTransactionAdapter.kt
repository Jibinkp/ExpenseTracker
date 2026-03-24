package com.example.expensetracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.Constants
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.entities.ExpenseWithCategory
import com.example.expensetracker.databinding.LayoutRecentTransactionItemBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils

class RecentTransactionAdapter(
    var items: List<ExpenseWithCategory>
) : RecyclerView.Adapter<RecentTransactionAdapter.RecentTransactionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentTransactionViewHolder {
        val binding = LayoutRecentTransactionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentTransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecentTransactionViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class RecentTransactionViewHolder(private val binding: LayoutRecentTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseWithCategory) {
            binding.tvDate.text =
                DateUtils.convertDateToOtherFormat(
                    item.expense.date,
                    Constants.DATE_FORMAT_YYYY_MM_DD,
                    Constants.DATE_FORMAT_MMM_DD_YYYY
                )
            val amount = "-₹${item.expense.amount.toInt()}"
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