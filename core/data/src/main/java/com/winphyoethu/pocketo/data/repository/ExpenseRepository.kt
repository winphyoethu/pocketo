package com.winphyoethu.pocketo.data.repository

import com.winphyoethu.pocketo.common.result.ExpenseErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.database.datasource.ExpenseLocalDatasource
import com.winphyoethu.pocketo.database.entity.ExpenseEntity
import com.winphyoethu.pocketo.model.expense.Expense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

/**
 * API for ExpenseRepository
 *
 * @see ExpenseRepositoryImpl for actual implementation
 *
 */
interface ExpenseRepository {

    /**
     * Create Expense
     *
     * @param accountId the account id of the user
     * @param description detail of expense
     * @param categoryId id of a category
     * @param currencyId id of a currency
     * @param amount amount used
     *
     * @return PocketoResult [PocketoResult] which type is Long (an id of created expense)
     *
     */
    suspend fun createExpense(
        accountId: Int, description: String, categoryId: Int, currencyId: Int, amount: Double
    ): PocketoResult<Long>

    /**
     * Update Expense
     *
     * @param id id of an expense
     * @param accountId the account id of the user
     * @param description detail of expense
     * @param categoryId id of a category
     * @param amount amount used
     *
     * @return PocketoResult [PocketoResult] which type is Integer (number of row affected)
     *
     */
    suspend fun updateExpense(
        id: Int,
        description: String,
        categoryId: Int,
        amount: Double
    ): PocketoResult<Int>

    /**
     * Delete Expense
     *
     * @param id id of an expense
     *
     * @return PocketoResult [PocketoResult] which type is Integer (number of row affected)
     *
     */
    suspend fun deleteExpense(id: Int): PocketoResult<Int>

    /**
     * Retrieve Expense based on startDate, endDate and accountId
     *
     * @param startDate start date in timestamp
     * @param endDate end date in timestamp
     *
     * @return PocketoResult [PocketoResult] which type is Integer (number of row affected)
     *
     */
    fun getExpense(startDate: Long, endDate: Long, accountId: Int): Flow<List<Expense>>

}

/**
 * Implementation of ExpenseRepository [ExpenseRepository]
 * @see ExpenseRepository
 *
 * @param datasource Local Data Source for Expense [ExpenseLocalDatasource]
 */
class ExpenseRepositoryImpl @Inject constructor(val datasource: ExpenseLocalDatasource) :
    ExpenseRepository {

    override suspend fun createExpense(
        accountId: Int,
        description: String,
        categoryId: Int,
        currencyId: Int,
        amount: Double
    ): PocketoResult<Long> {
        val result = datasource.createExpense(
            ExpenseEntity(
                accountId = accountId,
                description = description,
                categoryId = categoryId,
                currencyId = currencyId,
                amount = amount,
                createdAt = Calendar.getInstance().timeInMillis,
                updatedAt = Calendar.getInstance().timeInMillis
            )
        )

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(ExpenseErrorCode.ExpenseCreateFail)
        }
    }

    override suspend fun updateExpense(
        id: Int,
        description: String,
        categoryId: Int,
        amount: Double
    ): PocketoResult<Int> {
        val result = datasource.updateExpense(id, description, categoryId, amount)

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(ExpenseErrorCode.ExpenseUpdateFail)
        }
    }

    override suspend fun deleteExpense(id: Int): PocketoResult<Int> {
        val result = datasource.deleteExpense(id)

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(ExpenseErrorCode.ExpenseDeleteFail)
        }
    }

    override fun getExpense(startDate: Long, endDate: Long, accountId: Int): Flow<List<Expense>> {
        return datasource.getExpense(startDate, endDate, accountId).map {
            it.map { expenseFullInfo ->
                Expense(
                    id = expenseFullInfo.expense.id,
                    accountId = expenseFullInfo.expense.accountId,
                    description = expenseFullInfo.expense.description,
                    categoryId = expenseFullInfo.category.id,
                    category = expenseFullInfo.category.name,
                    amount = expenseFullInfo.expense.amount,
                    currencySymbol = expenseFullInfo.currency.symbol
                )
            }
        }
    }

}