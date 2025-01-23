package com.winphyoethu.pocketo.data.repository

import com.winphyoethu.pocketo.common.result.MonthlyExpenseErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.database.datasource.ExpenseLocalDatasource
import com.winphyoethu.pocketo.database.datasource.MonthlyExpenseLocalDatasource
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseEntity
import com.winphyoethu.pocketo.model.monthlyusage.MonthlyExpense
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Calendar
import javax.inject.Inject

/**
 * API for MonthlyExpenseRepository
 *
 * @see MonthlyExpenseRepositoryImpl for actual implementation
 *
 */
interface MonthlyExpenseRepository {

    /**
     * Create Monthly Expense
     *
     * @param accountId account id of an account
     * @param balance monthly balance of an account
     * @param currencyId id of the currency
     *
     * @return PocketoResult [PocketoResult] which type is Long (id of created monthly expense)
     */
    suspend fun createMonthlyExpense(
        accountId: Int,
        balance: Double,
        currencyId: Int
    ): PocketoResult<Long>

    /**
     * Create Monthly Expense
     *
     * @param id id of monthly expense
     * @param remainingBalance remaining monthly balance of an account
     *
     * @return PocketoResult [PocketoResult] which type is Integer (number of row affected)
     */
    suspend fun updateMonthlyExpense(
        id: Int,
        remainingBalance: Double,
    ): PocketoResult<Int>

    /**
     * Retrieve Monthly Expense
     *
     * @param startDate start date in timestamp
     * @param accountId account id of the user
     *
     * @return PocketoResult [PocketoResult] which type is MonthlyExpense [MonthlyExpense] and NULLABLE
     */
    suspend fun getMonthlyExpense(startDate: Long, accountId: Int): PocketoResult<MonthlyExpense?>

    /**
     * Retrieve Monthly Expense Flow
     *
     * @param startDate start date in timestamp
     * @param accountId account id of the user
     *
     * @return Flow of MonthlyExpense [MonthlyExpense]
     */
    fun getMonthlyExpenseFlow(startDate: Long, accountId: Int): Flow<MonthlyExpense>

}

/**
 * Implementation of MonthlyExpenseRepository [MonthlyExpenseRepository]
 * @see MonthlyExpenseRepository
 *
 * @param datasource Local Data Source for Monthly Expense [MonthlyExpenseLocalDatasource]
 */
class MonthlyExpenseRepositoryImpl @Inject constructor(val datasource: MonthlyExpenseLocalDatasource) :
    MonthlyExpenseRepository {

    override suspend fun createMonthlyExpense(
        accountId: Int,
        balance: Double,
        currencyId: Int,
    ): PocketoResult<Long> {
        val result = datasource.createMonthlyExpense(
            MonthlyExpenseEntity(
                accountId = accountId,
                balance = balance,
                remainingBalance = balance,
                currencyId = currencyId,
                createdAt = Calendar.getInstance().timeInMillis,
                updatedAt = Calendar.getInstance().timeInMillis
            )
        )

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(MonthlyExpenseErrorCode.MonthlyExpenseCreateFail)
        }
    }

    override suspend fun updateMonthlyExpense(
        id: Int, remainingBalance: Double
    ): PocketoResult<Int> {
        val result = datasource.updateMonthlyExpense(id, remainingBalance)

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(MonthlyExpenseErrorCode.MonthlyExpenseUpdateFail)
        }
    }

    override suspend fun getMonthlyExpense(
        startDate: Long,
        accountId: Int
    ): PocketoResult<MonthlyExpense?> {
        val result = datasource.getMonthlyExpense(startDate, accountId)

        return if (result != null) {
            return PocketoResult.Success(
                MonthlyExpense(
                    id = result.id,
                    accountId = result.accountId,
                    balance = result.balance,
                    remainingBalance = result.remainingBalance
                )
            )
        } else {
            PocketoResult.Error(MonthlyExpenseErrorCode.MonthlyExpenseRetrieveFail)
        }
    }

    override fun getMonthlyExpenseFlow(startDate: Long, accountId: Int): Flow<MonthlyExpense> {
        return datasource.getMonthlyExpenseFlow(startDate, accountId).map {
            MonthlyExpense(
                id = it.monthlyExpense.id,
                accountId = it.monthlyExpense.accountId,
                balance = it.monthlyExpense.balance,
                remainingBalance = it.monthlyExpense.remainingBalance,
                currencyName = it.currency.code,
                currencySymbol = it.currency.symbol
            )
        }
    }

}