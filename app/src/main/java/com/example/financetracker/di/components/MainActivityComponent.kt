package com.example.financetracker.di.components

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.financetracker.MainActivity
import com.example.financetracker.di.MainActivityScope
import com.example.financetracker.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@MainActivityScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class])
interface MainActivityComponent {

    fun viewModelProviderFactory(): ViewModelProvider.Factory

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance activity: ComponentActivity, appComponent: AppComponent): MainActivityComponent
    }
}