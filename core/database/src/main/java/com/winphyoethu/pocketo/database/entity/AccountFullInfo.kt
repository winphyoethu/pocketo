package com.winphyoethu.pocketo.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class AccountFullInfo (
    @Embedded val account: AccountEntity,
    @Relation(
        parentColumn = "currency_id",
        entityColumn = "id"
    )
    val currency: CurrencyEntity
)