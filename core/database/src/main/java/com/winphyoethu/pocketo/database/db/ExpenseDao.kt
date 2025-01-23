package com.winphyoethu.pocketo.database.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.winphyoethu.pocketo.database.entity.ExpenseFullInfo
import com.winphyoethu.pocketo.database.entity.ExpenseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createExpense(expense: ExpenseEntity): Long

    @Query("UPDATE expense SET description = :description AND category_id = :categoryId AND amount = :amount WHERE id = :id")
    suspend fun updateExpense(
        id: Int,
        description: String,
        categoryId: Int,
        amount: Double
    ): Int

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity): Int

    @Query("SELECT * FROM expense WHERE created_at > :startDate AND created_at < :endDate AND account_id = :accountId ORDER BY created_at DESC LIMIT :loadSize OFFSET :index")
    fun getExpensePaginate(
        startDate: Long,
        endDate: Long,
        accountId: Int,
        loadSize: Int,
        index: Int
    ): List<ExpenseEntity>

    @Transaction
    @Query("SELECT * FROM expense WHERE created_at > :startDate AND created_at < :endDate AND account_id = :accountId ORDER BY created_at DESC")
    fun getExpense(startDate: Long, endDate: Long, accountId: Int): Flow<List<ExpenseFullInfo>>

}