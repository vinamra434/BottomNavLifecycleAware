package com.app.fragmentsexample.ui.mainactivity

import android.os.Bundle
import android.util.Log
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.fragmentsexample.R
import com.app.fragmentsexample.ui.dashboardfragment.DashboardFragment
import com.app.fragmentsexample.ui.homefragment.HomeFragment
import com.app.fragmentsexample.ui.notificationfragment.NotificationFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val ACTIVE_FRAGMENT = "active"
    }

    private lateinit var mainViewModel: MainViewModel

    private var selectedItemId: Int = R.id.navigation_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)

        Log.d(
            "COMMON", "R.id.navigation_home = ${R.id.navigation_home}\n" +
                    "R.id.navigation_dashboard = ${R.id.navigation_dashboard}\n" +
                    "R.id.navigation_notifications = ${R.id.navigation_notifications}\n"
        )

        savedInstanceState?.run {

            when (getInt(ACTIVE_FRAGMENT)) {
                R.id.navigation_home -> {
                    mainViewModel.onHomeSelected()
                }
                R.id.navigation_dashboard -> {
                    mainViewModel.onDashboardSelected()
                }
                R.id.navigation_notifications -> {
                    mainViewModel.onNotificationSelected()
                }
            }
        }


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
                    mainViewModel.onNotificationSelected()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupObserver() {
        mainViewModel.homeSelected.observe(this, Observer {
            if (it) showFragment(R.id.navigation_home, HomeFragment.TAG, "#FFFF00")
        })
        mainViewModel.dashboardSelected.observe(this, Observer {
            if (it) showFragment(R.id.navigation_dashboard, DashboardFragment.TAG, "#FF00FF")
        })
        mainViewModel.notificationSelected.observe(this, Observer {
            if (it) showFragment(R.id.navigation_notifications, NotificationFragment.TAG, "#00FFFF")
        })

        mainViewModel.selectedItemId.observe(this, Observer {
            selectedItemId = it
            Log.d("COMMON", "after change ${mainViewModel.showName(selectedItemId)}")
        })
    }

    private fun showFragment(@IdRes newItemId: Int, tag: String, backgroundColor: String) {
        Log.d("COMMON", "show $tag")
        if (supportFragmentManager.findFragmentByTag(tag) == null) {
            val visibleFragment = supportFragmentManager.findFragmentById(R.id.container_fragment)
            mainViewModel.saveVisibleFragmentState(
                supportFragmentManager,
                visibleFragment,
                newItemId,
                selectedItemId
            )

        }

        mainViewModel.createFragment(supportFragmentManager, tag, backgroundColor, newItemId)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ACTIVE_FRAGMENT, selectedItemId)
    }

    override fun onBackPressed() {

        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 0) {
                        popBackStackImmediate()
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }
}