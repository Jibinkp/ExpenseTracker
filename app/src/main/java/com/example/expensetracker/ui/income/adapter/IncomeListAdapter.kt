package com.example.expensetracker.ui.income.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.Constants.DATE_FORMAT_MMM_DD_YYYY
import com.example.expensetracker.data.Constants.DATE_FORMAT_YYYY_MM_DD
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.databinding.LayoutIncomeItemBinding
import com.example.expensetracker.ui.utils.dateutils.DateUtils

class IncomeListAdapter(var items: List<IncomeItem>) :
    RecyclerView.Adapter<IncomeListAdapter.IncomeViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IncomeViewHolder {
        val binding =
            LayoutIncomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IncomeViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: IncomeViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class IncomeViewHolder(private val binding: LayoutIncomeItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: IncomeItem) {
            binding.txtIncomeTitle.text = item.source
            val amount = "+₹${item.amount}"
            binding.txtIncomeAmount.text = amount
            binding.txtDate.text = DateUtils.convertDateToOtherFormat(item.date,DATE_FORMAT_YYYY_MM_DD,DATE_FORMAT_MMM_DD_YYYY)
        }
    }
}