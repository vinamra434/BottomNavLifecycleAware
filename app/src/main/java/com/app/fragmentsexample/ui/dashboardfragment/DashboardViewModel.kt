package com.app.fragmentsexample.ui.dashboardfragment

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import com.app.fragmentsexample.R
import com.app.fragmentsexample.ui.childfragment.ChildFragment

class DashboardViewModel : ViewModel() {
    fun onCreateChildFragment(
        childFragmentManager: FragmentManager,
        parentName: String?
    ) {

        val count = childFragmentManager.backStackEntryCount
        val childText = parentName + (count + 1)
        childFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, ChildFragment.newInstance(childText))
            .addToBackStack(childText).commit()

    }
}