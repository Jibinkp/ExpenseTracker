package com.example.expensetracker.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.ui.adapter.transaction.TransactionAdapter
import com.example.expensetracker.ui.transaction.TransactionActivity

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun initView() {
        val database = ExpenseTrackerDatabase(requireContext())
        val repository = ExpenseTrackerRepository(database)
        val factory = HomeViewModelFactory(repository)

        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
        transactionAdapter = TransactionAdapter(listOf())

        /*binding.rvTransaction.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTransaction.adapter = transactionAdapter*/

        viewModel.getLastFiveTransactions().observe(viewLifecycleOwner) {
            transactionAdapter.items = it
            transactionAdapter.notifyDataSetChanged()
        }

        viewModel.getIncomeSum().observe(viewLifecycleOwner) {
            val total = it ?: 0.0
            //binding.txtIncomeAmountSum.text = "$total₹"
        }

        viewModel.getExpenseSum().observe(viewLifecycleOwner) {
            val total = it ?: 0.0
            //binding.txtExpenseAmountSum.text = "$total₹"
        }

        /*binding.txtSeeAllTransaction.setOnClickListener {
            startActivity(Intent(requireContext(), TransactionActivity::class.java))
        }*/
    }
}