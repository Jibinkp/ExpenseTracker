package com.example.expensetracker.ui.category

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.ExpenseTrackerDatabase.Companion.invoke
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.ActivityCategoryBinding
import com.example.expensetracker.ui.adapter.CategoryAdapter
import com.example.expensetracker.ui.home.HomeViewModel
import com.example.expensetracker.ui.home.HomeViewModelFactory

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        val database = ExpenseTrackerDatabase(this)
        val repository = ExpenseTrackerRepository(database)
        val factory = CategoryViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(CategoryViewModel::class.java)
        val categoryAdapter = CategoryAdapter(context = this, listOf())
        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.adapter = categoryAdapter

        viewModel.getCategory().observe(this, Observer {
            categoryAdapter.items = it
            categoryAdapter.notifyDataSetChanged()
        })

        binding.fabAddCategory.setOnClickListener {
            AddCategoryDialog(this, object : AddCategoryDialogListener {
                override fun onAddClickListener(item: CategoryItem) {
                    viewModel.upsertCategory(item)
                }
            }).show()
        }
    }
}