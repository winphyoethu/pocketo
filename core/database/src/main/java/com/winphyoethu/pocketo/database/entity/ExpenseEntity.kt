package com.winphyoethu.pocketo.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expense")
data class ExpenseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("account_id")
    val accountId: Int,
    @ColumnInfo("description")
    val description: String,
    @ColumnInfo("amount")
    val amount: Double,
    @ColumnInfo("category_id")
    val categoryId: Int,
    @ColumnInfo("currency_id")
    val currencyId: Int,
    @ColumnInfo("created_at")
    val createdAt: Long? = null,
    @ColumnInfo("updated_at")
    val updatedAt: Long
)