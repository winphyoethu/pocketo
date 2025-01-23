package com.winphyoethu.pocketo.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseAndCurrency
import com.winphyoethu.pocketo.database.entity.MonthlyExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MonthlyExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createMonthlyExpense(monthlyExpense: MonthlyExpenseEntity): Long

    @Query("UPDATE monthly_expense SET remaining_balance = :remainingBalance WHERE id = :id")
    suspend fun updateMonthlyExpense(id: Int, remainingBalance: Double): Int

    @Query("SELECT * from monthly_expense WHERE created_at >= :startDate AND account_id = :accountId")
    suspend fun getMonthlyExpense(startDate: Long, accountId: Int): MonthlyExpenseEntity?

    @Transaction
    @Query("SELECT * from monthly_expense WHERE created_at >= :startDate AND account_id = :accountId")
    fun getMonthlyExpenseFlow(startDate: Long, accountId: Int): Flow<MonthlyExpenseAndCurrency>

}