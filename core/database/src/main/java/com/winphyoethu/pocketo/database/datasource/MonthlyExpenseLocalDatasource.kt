package com.winphyoethu.pocketo.database.datasource

import com.winphyoethu.pocketo.database.db.MonthlyExpenseDao
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseAndCurrency
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface MonthlyExpenseLocalDatasource {

    suspend fun createMonthlyExpense(monthlyExpense: MonthlyExpenseEntity): Long

    suspend fun updateMonthlyExpense(id: Int, remainingBalance: Double): Int

    suspend fun getMonthlyExpense(startDate: Long, accountId: Int): MonthlyExpenseEntity?

    fun getMonthlyExpenseFlow(startDate: Long, accountId: Int): Flow<MonthlyExpenseAndCurrency>

}

class MonthlyExpenseLocalDatasourceImpl @Inject constructor(val monthlyExpenseDao: MonthlyExpenseDao) :
    MonthlyExpenseLocalDatasource {

    override suspend fun createMonthlyExpense(monthlyExpense: MonthlyExpenseEntity): Long {
        return monthlyExpenseDao.createMonthlyExpense(monthlyExpense)
    }

    override suspend fun updateMonthlyExpense(id: Int, remainingBalance: Double): Int {
        return monthlyExpenseDao.updateMonthlyExpense(id, remainingBalance)
    }

    override suspend fun getMonthlyExpense(startDate: Long, accountId: Int): MonthlyExpenseEntity? {
        return monthlyExpenseDao.getMonthlyExpense(startDate, accountId)
    }

    override fun getMonthlyExpenseFlow(
        startDate: Long,
        accountId: Int
    ): Flow<MonthlyExpenseAndCurrency> {
        return monthlyExpenseDao.getMonthlyExpenseFlow(startDate, accountId)
    }

}