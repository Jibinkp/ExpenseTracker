package com.example.expensetracker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.databinding.CategoryItemLayoutBinding

class CategoryAdapter(
    private val context: Context,
    var items: List<CategoryItem>
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val currentItem = items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class CategoryViewHolder(binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}