package com.winphyoethu.pocketo.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.winphyoethu.pocketo.database.entity.AccountEntity
import com.winphyoethu.pocketo.database.entity.CategoryEntity
import com.winphyoethu.pocketo.database.entity.CurrencyEntity
import com.winphyoethu.pocketo.database.entity.ExpenseEntity
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseEntity

@Database(
    version = 1, exportSchema = true,
    entities = [AccountEntity::class, CategoryEntity::class, ExpenseEntity::class, MonthlyExpenseEntity::class, CurrencyEntity::class]
)
abstract class PocketoDatabase : RoomDatabase() {

    abstract fun createAccountDao(): AccountDao

    abstract fun createCategoryDao(): CategoryDao

    abstract fun createExpenseDao(): ExpenseDao

    abstract fun createMonthlyExpenseDao(): MonthlyExpenseDao

    abstract fun createCurrencyDao(): CurrencyDao

}