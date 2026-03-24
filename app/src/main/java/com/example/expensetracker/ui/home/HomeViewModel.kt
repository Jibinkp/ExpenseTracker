package com.example.expensetracker.ui.home

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.expensetracker.data.PrimaryTypes
import com.example.expensetracker.data.database.entities.CategoryItem
import com.example.expensetracker.data.database.repository.ExpenseTrackerRepository
import com.example.expensetracker.ui.budget.model.BudgetModel
import com.example.expensetracker.ui.budget.model.TotalIncomeExpenseModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ExpenseTrackerRepository,
) : ViewModel() {

    val summaryLiveData = MediatorLiveData<List<BudgetModel>>()
    val totalIncomeExpenseData = MediatorLiveData<TotalIncomeExpenseModel>()

    private var income = 0.0
    private var needSpend = 0.0
    private var wantSpend = 0.0
    private var savingSpend = 0.0

    fun loadSummary(month: String) {

        summaryLiveData.addSource(repository.getSumOfCurrentMonthIncome(month)) {
            income = it ?: 0.0
            updateSummary()
        }

        summaryLiveData.addSource(repository.getNeedsSpend(month)) {
            needSpend = it ?: 0.0
            updateSummary()
        }

        summaryLiveData.addSource(repository.getWantsSpend(month)) {
            wantSpend = it ?: 0.0
            updateSummary()
        }

        summaryLiveData.addSource(repository.getSavingsSpend(month)) {
            savingSpend = it ?: 0.0
            updateSummary()
        }
    }

    private fun updateSummary() {

        val needTotal = income * 0.5
        val wantsTotal = income * 0.3
        val savingsTotal = income * 0.2

        val list = listOf(
            BudgetModel(PrimaryTypes.NEEDS, needTotal, needSpend, needTotal - needSpend),
            BudgetModel(PrimaryTypes.WANTS, wantsTotal, wantSpend, wantsTotal - wantSpend),
            BudgetModel(PrimaryTypes.SAVINGS, savingsTotal, savingSpend, savingsTotal - savingSpend)
        )

        summaryLiveData.value = list
    }

    fun getTotalIncomeExpense(selectedDate: String) {

        var totalIncome = 0.0
        var totalExpense = 0.0

        val incomeSource = repository.getSumOfCurrentMonthIncome(selectedDate)
        val expenseSource = repository.getSumOfExpense(selectedDate)

        totalIncomeExpenseData.addSource(incomeSource) { income ->
            totalIncome = income ?: 0.0
            updateTotal(totalIncome,totalExpense)
        }

        totalIncomeExpenseData.addSource(expenseSource) { expense ->
            totalExpense = expense ?: 0.0
            updateTotal(totalIncome,totalExpense)
        }
    }

    private fun updateTotal(income: Double, expense: Double) {
        totalIncomeExpenseData.value =
            TotalIncomeExpenseModel(income, expense, income - expense)
    }

    fun getLastFiveExpenses() = repository.getLastFiveExpenses()
}