package com.example.financetracker.di.mainactivity_component

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.financetracker.di.app_component.AppComponent
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