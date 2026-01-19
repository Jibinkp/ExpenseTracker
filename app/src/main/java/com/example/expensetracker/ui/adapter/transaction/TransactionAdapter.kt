package com.example.expensetracker.ui.adapter.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.database.entities.TransactionsItem
import com.example.expensetracker.databinding.LayoutTransactionItemBinding

class TransactionAdapter(var items: List<TransactionsItem>) :
    RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionViewHolder {
        val binding =
            LayoutTransactionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: TransactionViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class TransactionViewHolder(private val binding: LayoutTransactionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: TransactionsItem) {
            val amount = "${item.amount}₹"
            binding.txtAmount.text = amount
            binding.txtCategory.text = item.categoryId.toString()
            binding.txtDate.text = item.date
            binding.txtType.text = item.type
        }
    }
}