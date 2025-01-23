package com.winphyoethu.pocketo.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class MonthlyExpenseAndCurrency (
    @Embedded
    val monthlyExpense: MonthlyExpenseEntity,
    @Relation(
        parentColumn = "currency_id",
        entityColumn = "id"
    )
    val currency: CurrencyEntity
)