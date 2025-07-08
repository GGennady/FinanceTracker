package com.example.financetracker.di.app_component

import android.content.Context
import com.example.financetracker.data.AccountIdStorage
import com.example.financetracker.data.NetworkMonitor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideNetworkMonitor(context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }

    @Singleton
    @Provides
    fun provideAccountIdStorage(context: Context): AccountIdStorage {
        return AccountIdStorage(context)
    }
}