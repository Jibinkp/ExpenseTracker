package com.example.expensetracker.ui.home

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
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
        val factory = HomeViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        viewModel.getLastFiveTransactions().observe(this, Observer{

        })
    }
}