package com.example.expensetracker.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.ui.adapter.CategoryAdapter
import com.example.expensetracker.ui.category.CategoryActivity
import com.example.expensetracker.ui.transaction.TransactionActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initView()
    }

    private fun initView() {
        val database = ExpenseTrackerDatabase(this)
        val repository = ExpenseTrackerRepository(database)
        val factory = HomeViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)
        val categoryAdapter = CategoryAdapter(context = this, listOf())
        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.adapter = categoryAdapter

        viewModel.getLastFiveCategories().observe(this, Observer {
            categoryAdapter.items = it
            categoryAdapter.notifyDataSetChanged()
        })

        viewModel.getIncomeSum().observe(this, Observer {
            var total: Double = 0.0
            if (it != null) {
                total = it
            }
            val totalIncome = "$ $total"
            binding.txtIncomeAmountSum.text = totalIncome
        })

        viewModel.getExpenseSum().observe(this, Observer {
            var total: Double = 0.0
            if (it != null) {
                total = it
            }
            val totalExpenses = "$ $total"
            binding.txtExpenseAmountSum.text = totalExpenses
        })

        binding.txtSeeAllCategory.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

        binding.txtSeeAllTransaction.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
}