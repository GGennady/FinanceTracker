package com.example.financetracker.di.components

import android.content.Context
import com.example.financetracker.di.RepositoryModule
import com.example.financetracker.di.UtilsModule
import com.example.financetracker.di.retrofit_module.RetrofitClientModule
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