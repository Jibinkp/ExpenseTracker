package com.example.expensetracker.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.ui.adapter.category.CategoryAdapter
import com.example.expensetracker.ui.adapter.category.CategoryAdapterListener
import com.example.expensetracker.ui.adapter.transaction.TransactionAdapter
import com.example.expensetracker.ui.category.CategoryActivity
import com.example.expensetracker.ui.transaction.TransactionActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
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

    @SuppressLint("NotifyDataSetChanged")
    private fun initView() {
        val database = ExpenseTrackerDatabase(this)
        val repository = ExpenseTrackerRepository(database)
        val factory = HomeViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        val transactionAdapter = TransactionAdapter(listOf())
        binding.rvTransaction.layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = transactionAdapter


        viewModel.getLastFiveTransactions().observe(this, Observer {
            transactionAdapter.items = it
            transactionAdapter.notifyDataSetChanged()
        })

        viewModel.getIncomeSum().observe(this, Observer {
            var total: Double = 0.0
            if (it != null) {
                total = it
            }
            val totalIncome = "$total₹"
            binding.txtIncomeAmountSum.text = totalIncome
        })

        viewModel.getExpenseSum().observe(this, Observer {
            var total: Double = 0.0
            if (it != null) {
                total = it
            }
            val totalExpenses = "$total₹"
            binding.txtExpenseAmountSum.text = totalExpenses
        })

        binding.txtSeeAllTransaction.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }
    }
}