package com.example.expensetracker.ui.settings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensetracker.R
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.databinding.FragmentSettingsBinding
import com.example.expensetracker.ui.category.AddCategoryDialog
import com.example.expensetracker.ui.category.AddCategoryDialogListener
import com.example.expensetracker.ui.category.CategoryActivity

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.btnViewCategory.setOnClickListener {
            Intent(context, CategoryActivity::class.java).also {
                startActivity(it)
            }

        }
    }
}