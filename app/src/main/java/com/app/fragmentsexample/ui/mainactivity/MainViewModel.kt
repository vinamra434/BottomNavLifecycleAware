package com.app.fragmentsexample.ui.mainactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val homeSelected = MutableLiveData<Boolean>()
    val dashboardSelected = MutableLiveData<Boolean>()
    val notificationSelected = MutableLiveData<Boolean>()

    fun onHomeSelected() {
        homeSelected.value = true
    }

    fun onDashboardSelected() {
        dashboardSelected.value = true
    }

    fun oNotificationSelected() {
        notificationSelected.value = true
    }

}