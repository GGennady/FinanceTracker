package com.example.financetracker.di.mainactivity_component

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.financetracker.di.mainactivity_component.DaggerViewModelFactory
import com.example.financetracker.di.mainactivity_component.ViewModelKey
import com.example.financetracker.presentation.screens.Income.IncomeViewModel
import com.example.financetracker.presentation.screens.expenses.ExpensesViewModel
import com.example.financetracker.presentation.screens.expenses_history.ExpensesHistoryViewModel
import com.example.financetracker.presentation.screens.income_history.IncomeHistoryViewModel
import com.example.financetracker.presentation.screens.my_account.MyAccountViewModel
import com.example.financetracker.presentation.screens.my_articles.MyArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindsDaggerViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesViewModel::class)
    fun bindExpensesViewModelToViewModel(expensesViewModel: ExpensesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ExpensesHistoryViewModel::class)
    fun bindExpensesHistoryViewModelToViewModel(expensesHistoryViewModel: ExpensesHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeViewModel::class)
    fun bindIncomeViewModelToViewModel(incomeViewModel: IncomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(IncomeHistoryViewModel::class)
    fun bindIncomeHistoryViewModelToViewModel(incomeHistoryViewModel: IncomeHistoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyAccountViewModel::class)
    fun bindMyAccountViewModelToViewModel(myAccountViewModel: MyAccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyArticlesViewModel::class)
    fun bindMyArticlesViewModelToViewModel(myArticlesViewModel: MyArticlesViewModel): ViewModel
}