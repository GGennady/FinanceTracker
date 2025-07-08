package com.example.financetracker.di.app_component

import android.content.Context
import com.example.financetracker.di.app_component.RepositoryModule
import com.example.financetracker.di.app_component.UtilsModule
import com.example.financetracker.di.app_component.RetrofitClientModule
import com.example.financetracker.domain.FinanceRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, RetrofitClientModule::class, UtilsModule::class])
interface AppComponent {

    fun financeRepository(): FinanceRepository

    @Component.Factory
    interface Factory {

        fun create(@BindsInstance context: Context) : AppComponent
    }
}