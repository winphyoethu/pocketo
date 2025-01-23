package com.winphyoethu.pocketo.database.di

import android.content.Context
import com.winphyoethu.pocketo.database.db.AccountDao
import com.winphyoethu.pocketo.database.db.CategoryDao
import com.winphyoethu.pocketo.database.db.CurrencyDao
import com.winphyoethu.pocketo.database.db.DBProvider
import com.winphyoethu.pocketo.database.db.ExpenseDao
import com.winphyoethu.pocketo.database.db.MonthlyExpenseDao
import com.winphyoethu.pocketo.database.db.PocketoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providesPocketoDatabase(@ApplicationContext context: Context): PocketoDatabase {
        return DBProvider.create(context)
    }

    @Provides
    fun providesAccountDao(database: PocketoDatabase): AccountDao {
        return database.createAccountDao()
    }

    @Provides
    fun providesCategoryDao(database: PocketoDatabase): CategoryDao {
        return database.createCategoryDao()
    }

    @Provides
    fun providesExpenseDao(database: PocketoDatabase): ExpenseDao {
        return database.createExpenseDao()
    }

    @Provides
    fun providesMonthlyExpenseDao(database: PocketoDatabase): MonthlyExpenseDao {
        return database.createMonthlyExpenseDao()
    }

    @Provides
    fun providesCurrencyDao(database: PocketoDatabase): CurrencyDao {
        return database.createCurrencyDao()
    }

}