package com.winphyoethu.pocketo.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ExpenseFullInfo(
    @Embedded
    val expense: ExpenseEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryEntity,
    @Relation(
        parentColumn = "currency_id",
        entityColumn = "id"
    )
    val currency: CurrencyEntity
)