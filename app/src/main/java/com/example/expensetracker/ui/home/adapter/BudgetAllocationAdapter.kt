package com.example.expensetracker.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.databinding.LayoutBudgetAllocationItemBinding
import com.example.expensetracker.ui.budget.model.BudgetModel

class BudgetAllocationAdapter(
    var items: List<BudgetModel>
) : RecyclerView.Adapter<BudgetAllocationAdapter.BudgetAllocationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BudgetAllocationViewHolder {
        val binding = LayoutBudgetAllocationItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BudgetAllocationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BudgetAllocationViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BudgetAllocationViewHolder(private val binding: LayoutBudgetAllocationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BudgetModel) {
            val type = when (item.type) {
                PrimaryTypes.NEEDS -> "${item.type} ${ContextCompat.getString(binding.root.context,R.string.fifty_percentage)}"
                PrimaryTypes.WANTS -> "${item.type} ${
                    ContextCompat.getString(
                        binding.root.context,
                        R.string.thirty_percentage
                    )
                }"
                else -> "${item.type} ${
                    ContextCompat.getString(
                        binding.root.context,
                        R.string.twenty_percentage
                    )
                }"
            }
            binding.tvType.text = type
            binding.tvSpend.text =
                "₹${item.spend.toInt()} ${ContextCompat.getString(binding.root.context, R.string.spend)}"
            binding.tvRemaining.text =
                "₹${item.remaining.toInt()} ${ContextCompat.getString(binding.root.context, R.string.left)}"
            val progress = ((item.spend / item.total) * 100).toInt()
            binding.linearProgressIndicator.setProgress(progress, true)
        }
    }
}