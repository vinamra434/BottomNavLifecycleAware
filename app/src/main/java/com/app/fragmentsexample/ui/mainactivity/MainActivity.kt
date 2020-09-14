package com.app.fragmentsexample.ui.mainactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.fragmentsexample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupObserver()


        navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.navigation_home -> {
                    mainViewModel.onHomeSelected()
                    true
                }
                R.id.navigation_dashboard -> {
                    mainViewModel.onDashboardSelected()
                    true
                }
                R.id.navigation_notifications -> {
                    mainViewModel.oNotificationSelected()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupObserver() {
        mainViewModel.homeSelected.observe(this, Observer {
            if (it) showHomeFragment()
        })
        mainViewModel.dashboardSelected.observe(this, Observer {
            if (it) showDashboardFragment()
        })
        mainViewModel.notificationSelected.observe(this, Observer {
            if (it) showNotificationFragment()
        })
    }

    private fun showNotificationFragment() {

    }

    private fun showDashboardFragment() {

    }

    private fun showHomeFragment() {

    }
}