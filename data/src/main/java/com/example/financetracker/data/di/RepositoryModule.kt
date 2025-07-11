package com.example.financetracker.data.di

import com.example.financetracker.data.repository.FinanceRepositoryImpl
import com.example.financetracker.domain.FinanceRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {

    @Binds
    fun bindFinanceRepositoryImplToFinanceRepository(impl: FinanceRepositoryImpl): FinanceRepository
}