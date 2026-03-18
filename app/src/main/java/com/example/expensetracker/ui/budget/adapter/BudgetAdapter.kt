package com.example.expensetracker.ui.budget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.databinding.LayoutBudgetItemBinding
import com.example.expensetracker.ui.budget.model.BudgetModel

class BudgetAdapter(var items: List<BudgetModel>) :
    RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BudgetViewHolder {
        val binding =
            LayoutBudgetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: BudgetViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class BudgetViewHolder(val binding: LayoutBudgetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: BudgetModel){
            val type = when(item.type){
                PrimaryTypes.NEEDS -> "${item.type} (50%)"
                PrimaryTypes.WANTS -> "${item.type} (30%)"
                else -> "${item.type} (20%)"
            }
            binding.tvType.text = type
            binding.tvTotal.text = "₹${item.total.toInt()}"
            binding.tvSpend.text = "₹${item.spend.toInt()}"
            binding.tvRemaining.text = "₹${item.remaining.toInt()}"
            val progress = ((item.spend / item.total) * 100).toInt()
            binding.tvPercentage.text = "$progress%"
            binding.linearProgressIndicator.setProgress(progress, true)
        }

    }
}