package com.winphyoethu.pocketo.model.expense

data class Expense (
    val id: Int,
    val accountId: Int,
    val description: String,
    val categoryId: Int,
    val category: String,
    val amount: Double,
    val currencySymbol: String
)

val mockExpense = Expense(
    id = 0,
    accountId = 0,
    description = "With Peter Brown",
    categoryId = 0,
    category = "Food",
    amount = 100.0,
    currencySymbol = "$"
)