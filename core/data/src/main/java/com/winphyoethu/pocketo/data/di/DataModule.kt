package com.winphyoethu.pocketo.data.di

import com.winphyoethu.pocketo.data.repository.AccountRepository
import com.winphyoethu.pocketo.data.repository.AccountRepositoryImpl
import com.winphyoethu.pocketo.data.repository.CategoryRepository
import com.winphyoethu.pocketo.data.repository.CategoryRepositoryImpl
import com.winphyoethu.pocketo.data.repository.CurrencyRepository
import com.winphyoethu.pocketo.data.repository.CurrencyRepositoryImpl
import com.winphyoethu.pocketo.data.repository.ExpenseRepository
import com.winphyoethu.pocketo.data.repository.ExpenseRepositoryImpl
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepository
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {

    @Binds
    abstract fun bindsAccountRepository(repository: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsCategoryRepository(repository: CategoryRepositoryImpl): CategoryRepository

    @Binds
    abstract fun bindsExpenseRepository(repository: ExpenseRepositoryImpl): ExpenseRepository

    @Binds
    abstract fun bindsMonthlyExpenseRepository(repository: MonthlyExpenseRepositoryImpl): MonthlyExpenseRepository

    @Binds
    abstract fun bindsCurrencyRepository(repository: CurrencyRepositoryImpl): CurrencyRepository

}