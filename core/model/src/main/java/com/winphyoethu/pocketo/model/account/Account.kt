package com.winphyoethu.pocketo.model.account

data class Account(
    val id: Int,
    val name: String,
    val income: Double,
    val currencyId: Int = -1,
    val currencyCode: String = "",
    val currencySymbol: String = ""
)