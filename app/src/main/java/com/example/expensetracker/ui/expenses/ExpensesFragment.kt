package com.example.expensetracker.ui.expenses

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensetracker.R
import com.example.expensetracker.databinding.FragmentExpensesBinding
import com.example.expensetracker.databinding.FragmentHomeBinding

class ExpensesFragment : Fragment() {
    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!


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
        binding.fbAddEditExpense.setOnClickListener {
            context?.let {
                AddEditExpenseDialog(it).show()
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