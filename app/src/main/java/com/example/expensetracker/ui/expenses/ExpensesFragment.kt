package com.example.expensetracker.ui.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.ExpenseTrackerDatabase.Companion.invoke
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentExpensesBinding
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.ui.expenses.adapter.ExpenseAdapter
import com.example.expensetracker.ui.income.IncomeViewModel
import com.example.expensetracker.ui.income.IncomeViewModelFactory

class ExpensesFragment : Fragment() {
    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ExpenseViewModel
    private lateinit var expenseAdapter: ExpenseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        val database = ExpenseTrackerDatabase(requireContext())
        val repository = ExpenseTrackerRepository(database)
        val factory = ExpenseViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[ExpenseViewModel::class]
        expenseAdapter = ExpenseAdapter(listOf())
        binding.rvExpense.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExpense.adapter = expenseAdapter

        viewModel.getExpenses().observe(viewLifecycleOwner) {
            expenseAdapter.items = it
            expenseAdapter.notifyDataSetChanged()
        }

        binding.fbAddEditExpense.setOnClickListener {
            context?.let {
                AddEditExpenseDialog(it, viewModel, viewLifecycleOwner, object :
                    AddEditExpenseDialogListener {
                    override fun onSaveExpenseClickListener(expenseItem: ExpenseItem) {
                        viewModel.upsertExpense(expenseItem)
                    }
                }).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExpensesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}