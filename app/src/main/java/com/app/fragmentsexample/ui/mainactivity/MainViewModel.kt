package com.app.fragmentsexample.ui.mainactivity

import android.util.SparseArray
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.fragmentsexample.R
import com.app.fragmentsexample.ui.dashboardfragment.DashboardFragment
import com.app.fragmentsexample.ui.homefragment.HomeFragment
import com.app.fragmentsexample.ui.notificationfragment.NotificationFragment

class MainViewModel : ViewModel() {

    val homeSelected = MutableLiveData<Boolean>()
    val dashboardSelected = MutableLiveData<Boolean>()
    val notificationSelected = MutableLiveData<Boolean>()
    private val sparseArray = SparseArray<Fragment.SavedState>()
    val selectedItemId = MutableLiveData<Int>()

    fun onHomeSelected() {
        homeSelected.postValue(true)
    }

    fun onDashboardSelected() {
        dashboardSelected.postValue(true)
    }

    fun onNotificationSelected() {
        notificationSelected.postValue(true)
    }

    /*manager to save state of visible fragment, newItemId is newly selected item and oldItemId is of visible fragment*/
    fun saveVisibleFragmentState(
        manager: FragmentManager,
        visibleFragment: Fragment?,
        newItemId: Int,
        oldItemId: Int
    ) {
        sparseArray.put(oldItemId, visibleFragment?.let { manager.saveFragmentInstanceState(it) })
        selectedItemId.postValue(newItemId)
    }

    fun showName(selectedItemId: Int) = when (selectedItemId) {
        R.id.navigation_home -> {
            "R.id.navigation_home"

        }
        R.id.navigation_dashboard -> {
            "R.id.navigation_dashboard"

        }
        R.id.navigation_notifications -> {
            "R.id.navigation_notifications"

        }

        else -> ""
    }

    fun createFragment(
        fragmentManager: FragmentManager,
        tag: String,
        backgroundColor: String,
        itemId: Int
    ) {

        val fragment: Fragment? = when (tag) {
            HomeFragment.TAG -> HomeFragment.newInstance(backgroundColor, "Home")
            NotificationFragment.TAG -> NotificationFragment.newInstance(
                backgroundColor,
                "Notification"
            )
            DashboardFragment.TAG -> DashboardFragment.newInstance(backgroundColor, "Dashboard")
            else -> null
        }

        fragment?.run {
            setInitialSavedState(sparseArray[itemId])
            fragmentManager.beginTransaction().replace(R.id.container_fragment, this).commit()
        }


    }

}