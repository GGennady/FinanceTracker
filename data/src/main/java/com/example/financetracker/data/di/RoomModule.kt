package com.example.financetracker.data.di

import android.content.Context
import androidx.room.Room
import com.example.financetracker.data.db.CategoryDao
import com.example.financetracker.data.db.FinanceDatabase
import com.example.financetracker.data.db.TransactionDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): FinanceDatabase {
        return Room.databaseBuilder(context, FinanceDatabase::class.java, "finance_database.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(db: FinanceDatabase): TransactionDao {
        return db.transactionDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: FinanceDatabase): CategoryDao {
        return db.categoryDao()
    }
}