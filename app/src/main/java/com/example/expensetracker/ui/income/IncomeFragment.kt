package com.example.expensetracker.ui.income

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.Constants.DATE_FORMAT_MMMM_YYYY
import com.example.expensetracker.data.Constants.DATE_FORMAT_YYYY_MM
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.ExpenseTrackerDatabase.Companion.invoke
import com.example.expensetracker.data.database.entities.IncomeItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentExpensesBinding
import com.example.expensetracker.databinding.FragmentIncomeBinding
import com.example.expensetracker.ui.home.HomeViewModel
import com.example.expensetracker.ui.home.HomeViewModelFactory
import com.example.expensetracker.ui.income.adapter.IncomeListAdapter
import com.example.expensetracker.ui.utils.dateutils.DateUtils

class IncomeFragment : Fragment() {
    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: IncomeViewModel
    private lateinit var incomeListAdapter: IncomeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnAddIncome.setOnClickListener {
            context?.let {
                AddEditIncomeDialog(it, object : AddEditIncomeDialogListener {
                    override fun onSaveClickListener(incomeItem: IncomeItem) {
                        viewModel.upsertIncome(incomeItem)
                    }

                }).show()
            }
        }

        val database = ExpenseTrackerDatabase(requireContext())
        val repository = ExpenseTrackerRepository(database)
        val factory = IncomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[IncomeViewModel::class]
        incomeListAdapter = IncomeListAdapter(listOf())
        binding.rvIncome.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIncome.adapter = incomeListAdapter

        viewModel.getIncomes().observe(viewLifecycleOwner) {
            incomeListAdapter.items = it
            incomeListAdapter.notifyDataSetChanged()
        }

        viewModel.getSumOfCurrentMonthIncome(DateUtils.getCurrentDateAndTime(DATE_FORMAT_YYYY_MM).toString())
            .observe(viewLifecycleOwner) {
                var amount: String? = null
                if (it != null){
                    amount = "₹${it.toInt()}"
                }else{
                    amount = "₹0"
                }

                binding.tvSumOfIncome.text = amount
            }

        binding.tvDate.text = DateUtils.getCurrentDateAndTime(DATE_FORMAT_MMMM_YYYY)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            IncomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}