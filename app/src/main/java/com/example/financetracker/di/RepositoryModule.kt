package com.example.financetracker.di

import com.example.financetracker.data.repository.FinanceRepositoryImpl
import com.example.financetracker.domain.FinanceRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Provides repository bindings for dependency injection.
 *
 * This module binds repository interfaces to their concrete implementations...
 * ...to make them available for injection across the app.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the FinanceRepository implementation to its interface.
     *
     * This allows the dependency injection framework to provide an instance...
     * ...of FinanceRepository wherever it is required.
     *
     * @param impl The concrete implementation of FinanceRepository.
     * @return An instance of FinanceRepository interface.
     */
    @Binds
    abstract fun bindFinanceRepository(impl: FinanceRepositoryImpl): FinanceRepository
}