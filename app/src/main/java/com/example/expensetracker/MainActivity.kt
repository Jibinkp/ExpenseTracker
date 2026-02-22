package com.example.expensetracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.expensetracker.databinding.ActivityMainBinding
import com.example.expensetracker.ui.home.HomeFragment
import com.example.expensetracker.ui.reminder.ReminderFragment
import com.example.expensetracker.ui.savings.SavingsFragment
import com.example.expensetracker.ui.settings.SettingsFragment

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
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        loadFragment(HomeFragment())
        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.iHome -> {
                    loadFragment(HomeFragment.newInstance())
                    true
                }

                R.id.iSavings -> {
                    loadFragment(SavingsFragment.newInstance())
                    true
                }

                R.id.iReminder -> {
                    loadFragment(ReminderFragment.newInstance())
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
        transaction.replace(binding.container.id,fragment)
        transaction.commit()
    }
}