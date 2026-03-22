package com.example.expensetracker.ui.home

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.R
import com.example.expensetracker.data.Constants.DATE_FORMAT_MMMM_YYYY
import com.example.expensetracker.data.Constants.DATE_FORMAT_YYYY_MM
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.ExpenseTrackerDatabase
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.databinding.FragmentHomeBinding
import com.example.expensetracker.ui.home.adapter.BudgetAllocationAdapter
import com.example.expensetracker.ui.home.adapter.RecentTransactionAdapter
import com.example.expensetracker.ui.utils.dateutils.DateUtils
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel

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
        val currentDate = DateUtils.getCurrentDateAndTime(DATE_FORMAT_MMMM_YYYY)
        binding.tvDate.text = currentDate
        viewModel.getTotalIncomeExpense(
            LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM))
        )
        viewModel.totalIncomeExpenseData.observe(viewLifecycleOwner) {
            binding.tvIncome.text = "₹${it.incomeTotal?.toInt()}"
            binding.tvExpense.text = "₹${it.expenseTotal?.toInt()}"
            binding.tvBalance.text = "₹${it.balanceTotal?.toInt()}"
        }

        val recentTransactionAdapter = RecentTransactionAdapter(listOf())
        binding.rvRecentExpense.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRecentExpense.adapter = recentTransactionAdapter

        viewModel.getLastFiveExpenses().observe(viewLifecycleOwner) {
            recentTransactionAdapter.items = it
            recentTransactionAdapter.notifyDataSetChanged()
        }

        val budgetAllocationAdapter = BudgetAllocationAdapter(listOf())
        binding.rvBudgetAllocation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBudgetAllocation.adapter = budgetAllocationAdapter

        viewModel.summaryLiveData.observe(viewLifecycleOwner) {
            budgetAllocationAdapter.items = it
            budgetAllocationAdapter.notifyDataSetChanged()
            val entries: ArrayList<PieEntry> = ArrayList()
            for (i in it) {
                when(i.type){
                    PrimaryTypes.NEEDS ->{
                        binding.tvTotalSpendOfNeeds.text = "₹${i.spend.toInt()}"
                    }

                    PrimaryTypes.WANTS ->{
                        binding.tvTotalSpendOfWants.text = "₹${i.spend.toInt()}"
                    }

                    PrimaryTypes.SAVINGS ->{
                        binding.tvTotalSpendOfSavings.text = "₹${i.spend.toInt()}"
                    }
                }
                val percent = if (i.total != 0.0) ((i.spend / i.total) * 100).toFloat() else 0f
                entries.add(PieEntry(percent))
            }
            loadPieChart(entries)
        }

        viewModel.loadSummary(
            LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_YYYY_MM))
        )

        binding.tvViewAll.setOnClickListener {

        }

    }

    fun loadPieChart(entries: ArrayList<PieEntry>) {
        binding.pieChart.setUsePercentValues(true)
        binding.pieChart.description.isEnabled = false
        binding.pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
        binding.pieChart.setDragDecelerationFrictionCoef(0.95f)
        binding.pieChart.isDrawHoleEnabled = true
        binding.pieChart.setHoleColor(Color.WHITE)
        binding.pieChart.setTransparentCircleColor(Color.WHITE)
        binding.pieChart.setTransparentCircleAlpha(110)
        binding.pieChart.holeRadius = 58f
        binding.pieChart.transparentCircleRadius = 61f
        binding.pieChart.setDrawCenterText(true)
        binding.pieChart.setRotationAngle(0f)
        binding.pieChart.isRotationEnabled = true
        binding.pieChart.isHighlightPerTapEnabled = true
        binding.pieChart.animateY(1400, Easing.EaseInOutQuad)
        binding.pieChart.legend.isEnabled = false
        binding.pieChart.setDrawEntryLabels(true)
        binding.pieChart.setEntryLabelColor(Color.WHITE)
        binding.pieChart.setEntryLabelTextSize(12f)
        val dataSet =
            PieDataSet(entries, ContextCompat.getString(requireContext(), R.string.budget))
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 5f
        val colors: ArrayList<Int> = ArrayList()
        colors.add(ContextCompat.getColor(requireContext(), R.color.red))
        colors.add(ContextCompat.getColor(requireContext(), R.color.orange))
        colors.add(ContextCompat.getColor(requireContext(), R.color.purple_200))
        dataSet.colors = colors
        dataSet.setDrawValues(false)
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.DEFAULT_BOLD)
        data.setValueTextColor(Color.WHITE)
        binding.pieChart.setData(data)
        binding.pieChart.highlightValues(null)
        binding.pieChart.invalidate()
    }
}