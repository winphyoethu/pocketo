package com.winphyoethu.pocketo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_expense")
data class MonthlyExpenseEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("account_id")
    val accountId: Int,
    @ColumnInfo("balance")
    val balance: Double,
    @ColumnInfo("remaining_balance")
    val remainingBalance: Double,
    @ColumnInfo("currency_id")
    val currencyId: Int,
    @ColumnInfo("created_at")
    val createdAt: Long? = null,
    @ColumnInfo("updated_at")
    val updatedAt: Long
)