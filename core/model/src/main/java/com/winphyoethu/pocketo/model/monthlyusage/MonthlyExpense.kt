package com.winphyoethu.pocketo.model.monthlyusage

data class MonthlyExpense(
    val id: Int,
    val accountId: Int,
    val balance: Double,
    val remainingBalance: Double,
    val currencyName: String = "",
    val currencySymbol: String = "",
    val accountName: String = ""
)

val mockMonthlyExpense = MonthlyExpense(
    id = 1,
    accountId = 0,
    balance = 1000.0,
    remainingBalance = 1000.0
)