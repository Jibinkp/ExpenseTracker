package com.example.expensetracker.ui.expenses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.entities.ExpenseItem
import com.example.expensetracker.data.database.entities.ExpenseWithCategory
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentExpensesBinding
import com.example.expensetracker.ui.expenses.adapter.ExpenseAdapter
import com.example.expensetracker.ui.expenses.adapter.ExpenseAdapterListener
import com.example.expensetracker.ui.utils.dialogutils.DialogClickListener
import com.example.expensetracker.ui.utils.dialogutils.DialogUtils

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
        expenseAdapter = ExpenseAdapter(listOf(),expenseAdapterListener)
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

    private val expenseAdapterListener = object : ExpenseAdapterListener{

        override fun onDeleteClickListener(item: ExpenseWithCategory) {
            DialogUtils(requireContext()).showDialog("","Do you really want to delete this?",dialogClickListener,item)
        }
    }

    private val dialogClickListener = object : DialogClickListener{

        override fun onClickAcceptListener(item: Any?) {
            val item = item as ExpenseWithCategory
            Toast.makeText(requireContext(), "${item.expense.amount}", Toast.LENGTH_SHORT).show()
        }

        override fun onClickDeclineListener() {

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