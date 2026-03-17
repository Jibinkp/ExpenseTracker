package com.example.expensetracker.ui.budget

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.Constants.DATE_FORMAT_YYYY_MM
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.ExpenseTrackerDatabase.Companion.invoke
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentBudgetBinding
import com.example.expensetracker.databinding.FragmentExpensesBinding
import com.example.expensetracker.ui.budget.adapter.BudgetAdapter
import com.example.expensetracker.ui.expenses.ExpenseViewModel
import com.example.expensetracker.ui.expenses.ExpenseViewModelFactory
import com.example.expensetracker.ui.utils.dateutils.DateUtils
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class BudgetFragment : Fragment() {
    private lateinit var _binding: FragmentBudgetBinding
    private val binding get() = _binding
    private lateinit var viewModel: BudgetViewModel
    private lateinit var budgetAdapter: BudgetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BudgetFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val database = ExpenseTrackerDatabase(requireContext())
        val repository = ExpenseTrackerRepository(database)
        val factory = BudgetViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[BudgetViewModel::class]

        budgetAdapter = BudgetAdapter(listOf())
        binding.rvBudget.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBudget.adapter = budgetAdapter

        viewModel.summaryLiveData.observe(viewLifecycleOwner) {
            budgetAdapter.items = it
            budgetAdapter.notifyDataSetChanged()
        }

        binding.ivPrevious.setOnClickListener {
            viewModel.previousMonth()
        }

        binding.ivNext.setOnClickListener {
            viewModel.nextMonth()
        }

        viewModel.monthText.observe(viewLifecycleOwner) {
            binding.tvMonth.text = it
        }

        viewModel.currentDate.observe(viewLifecycleOwner) {
            viewModel.loadSummary(it.format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM)))
            viewModel.getTotalIncomeExpense(it.format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM)))
        }

        viewModel.totalIncomeExpenseData.observe(viewLifecycleOwner) {
            binding.tvTotalIncome.text = "₹${it.incomeTotal}"
            binding.tvTotalExpense.text = "₹${it.expenseTotal}"
            binding.tvTotalBalance.text = "₹${it.balanceTotal}"
        }

    }

}