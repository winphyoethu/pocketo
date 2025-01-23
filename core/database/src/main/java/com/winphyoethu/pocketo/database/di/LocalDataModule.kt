package com.winphyoethu.pocketo.database.di

import com.winphyoethu.pocketo.database.datasource.AccountLocalDatasource
import com.winphyoethu.pocketo.database.datasource.AccountLocalDatasourceImpl
import com.winphyoethu.pocketo.database.datasource.CategoryLocalDatasource
import com.winphyoethu.pocketo.database.datasource.CategoryLocalDatasourceImpl
import com.winphyoethu.pocketo.database.datasource.CurrencyLocalDatasource
import com.winphyoethu.pocketo.database.datasource.CurrencyLocalDatasourceImpl
import com.winphyoethu.pocketo.database.datasource.ExpenseLocalDatasource
import com.winphyoethu.pocketo.database.datasource.ExpenseLocalDatasourceImpl
import com.winphyoethu.pocketo.database.datasource.MonthlyExpenseLocalDatasource
import com.winphyoethu.pocketo.database.datasource.MonthlyExpenseLocalDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class LocalDataModule {

    @Binds
    abstract fun bindsAccountLocalDatasource(datasource: AccountLocalDatasourceImpl): AccountLocalDatasource

    @Binds
    abstract fun bindsCategoryLocalDatasource(datasource: CategoryLocalDatasourceImpl): CategoryLocalDatasource

    @Binds
    abstract fun bindsExpenseLocalDatasource(datasource: ExpenseLocalDatasourceImpl): ExpenseLocalDatasource

    @Binds
    abstract fun bindsMonthlyExpenseLocalDatasource(datasource: MonthlyExpenseLocalDatasourceImpl): MonthlyExpenseLocalDatasource

    @Binds
    abstract fun bindsCurrencyLocalDatasource(datasource: CurrencyLocalDatasourceImpl): CurrencyLocalDatasource

}