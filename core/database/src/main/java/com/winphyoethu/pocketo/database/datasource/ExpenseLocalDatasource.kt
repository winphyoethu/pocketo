package com.winphyoethu.pocketo.database.datasource

import com.winphyoethu.pocketo.database.db.ExpenseDao
import com.winphyoethu.pocketo.database.entity.ExpenseFullInfo
import com.winphyoethu.pocketo.database.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ExpenseLocalDatasource {

    suspend fun createExpense(expense: ExpenseEntity): Long

    suspend fun deleteExpense(id: Int): Int

    suspend fun updateExpense(
        id: Int,
        description: String,
        categoryId: Int,
        amount: Double
    ): Int

    fun getExpensePaginate(
        startDate: Long,
        endDate: Long,
        accountId: Int,
        loadSize: Int,
        index: Int
    ): List<ExpenseEntity>

    fun getExpense(
        startDate: Long,
        endDate: Long,
        accountId: Int
    ): Flow<List<ExpenseFullInfo>>

}

class ExpenseLocalDatasourceImpl @Inject constructor(val expenseDao: ExpenseDao) :
    ExpenseLocalDatasource {

    override suspend fun createExpense(expense: ExpenseEntity): Long {
        return expenseDao.createExpense(expense)
    }

    override suspend fun deleteExpense(id: Int): Int {
//        return expenseDao.deleteExpense(id)
        return 0
    }

    override suspend fun updateExpense(
        id: Int,
        description: String,
        categoryId: Int,
        amount: Double
    ): Int {
        return expenseDao.updateExpense(id, description, categoryId, amount)
    }

    override fun getExpensePaginate(
        startDate: Long,
        endDate: Long,
        accountId: Int,
        loadSize: Int,
        index: Int
    ): List<ExpenseEntity> {
        return expenseDao.getExpensePaginate(startDate, endDate, accountId, loadSize, index)
    }

    override fun getExpense(
        startDate: Long,
        endDate: Long,
        accountId: Int
    ): Flow<List<ExpenseFullInfo>> {
        return expenseDao.getExpense(startDate, endDate, accountId)
    }

}