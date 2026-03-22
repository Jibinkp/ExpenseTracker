package com.example.expensetracker.ui.budget.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
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
            if (progress<= 50){
                binding.tvPercentage.setTextColor(ContextCompat.getColor(binding.root.context,R.color.purple_200))
                binding.tvPercentage.background = ContextCompat.getDrawable(binding.root.context,R.drawable.savings_background)
            }else if (progress in 51..99){
                binding.tvPercentage.setTextColor(ContextCompat.getColor(binding.root.context,R.color.orange))
                binding.tvPercentage.background = ContextCompat.getDrawable(binding.root.context,R.drawable.wants_background)
            }else{
                binding.tvPercentage.setTextColor(ContextCompat.getColor(binding.root.context,R.color.red))
                binding.tvPercentage.background = ContextCompat.getDrawable(binding.root.context,R.drawable.expense_background)
            }
            if (item.remaining >= 0){
                binding.tvRemaining.setTextColor(ContextCompat.getColor(binding.root.context,R.color.purple_200))
            }else{
                binding.tvRemaining.setTextColor(ContextCompat.getColor(binding.root.context,R.color.red))
            }
            binding.tvPercentage.text = "$progress%"
            binding.linearProgressIndicator.setProgress(progress, true)
        }

    }
}