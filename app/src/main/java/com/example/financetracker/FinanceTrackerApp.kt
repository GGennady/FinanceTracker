package com.example.financetracker

import android.app.Application
import com.example.financetracker.di.app_component.AppComponent
import com.example.financetracker.di.app_component.DaggerAppComponent

class FinanceTrackerApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(this)
    }
}

//val Context.appComponent: AppComponent
//    get() = when(this) {
//        is FinanceTrackerApp -> this.appComponent
//        else -> (this.applicationContext as FinanceTrackerApp).appComponent
//    }