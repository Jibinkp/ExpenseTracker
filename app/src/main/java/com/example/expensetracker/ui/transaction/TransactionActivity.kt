package com.example.expensetracker.ui.transaction

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProviders
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.entities.TransactionsItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.ActivityTransactionBinding

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

    private fun initView() {
        val database = ExpenseTrackerDatabase(this)
        val repository = ExpenseTrackerRepository(database)
        val factory = TransactionViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(TransactionViewModel::class)

        binding.fbAddTransaction.setOnClickListener {
            AddTransactionDialog(this, object : AddTransactionDialogListener {
                override fun onAddClickListener(item: TransactionsItem) {
                    viewModel.upsertTransaction(item)
                }
            }).show()
        }
    }
}