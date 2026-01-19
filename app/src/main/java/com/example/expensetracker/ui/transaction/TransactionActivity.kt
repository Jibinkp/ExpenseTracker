package com.example.expensetracker.ui.transaction

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
import com.example.expensetracker.data.database.entities.TransactionsItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.ActivityTransactionBinding
import com.example.expensetracker.ui.adapter.transaction.TransactionAdapter

class TransactionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTransactionBinding.inflate(layoutInflater)
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
        val factory = TransactionViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory)[TransactionViewModel::class]
        val transactionAdapter = TransactionAdapter(listOf())
        binding.rvTransaction.layoutManager = LinearLayoutManager(this)
        binding.rvTransaction.adapter = transactionAdapter

        viewModel.getTransaction().observe(this, Observer {
            transactionAdapter.items = it
            transactionAdapter.notifyDataSetChanged()
        })

        binding.fbAddTransaction.setOnClickListener {
            AddTransactionDialog(this, object : AddTransactionDialogListener {
                override fun onAddClickListener(item: TransactionsItem) {
                    viewModel.upsertTransaction(item)
                }
            }).show()
        }
    }
}