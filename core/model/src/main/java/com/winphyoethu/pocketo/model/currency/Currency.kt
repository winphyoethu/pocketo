package com.winphyoethu.pocketo.model.currency

data class Currency(
    val id: Int,
    val name: String,
    val code: String,
    val symbol: String
)

val mockCurrency = Currency(
    id = 1,
    name = "Australian Dollar",
    code = "AUD",
    symbol = "$"
)