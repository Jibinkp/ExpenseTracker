package com.example.expensetracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.ui.budget.BudgetFragment
import com.example.expensetracker.ui.expenses.ExpensesFragment
import com.example.expensetracker.ui.home.HomeFragment
import com.example.expensetracker.ui.income.IncomeFragment
import com.example.expensetracker.ui.settings.SettingsFragment
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import com.microsoft.appcenter.distribute.Distribute

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        AppCenter.start(application, ContextCompat.getString(this,R.string.app_center_secret_key), Analytics::class.java, Crashes::class.java,
            Distribute::class.java)
        loadFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.iHome -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }

                R.id.iExpense -> {
                    loadFragment(ExpensesFragment.newInstance())
                    true
                }

                R.id.iBudget -> {
                    loadFragment(BudgetFragment.newInstance())
                    true
                }

                R.id.iIncome -> {
                    loadFragment(IncomeFragment.newInstance())
                    true
                }

                R.id.iSettings -> {
                    loadFragment(SettingsFragment.newInstance())
                    true
                }

                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(binding.container.id, fragment)
        transaction.commit()
    }
}