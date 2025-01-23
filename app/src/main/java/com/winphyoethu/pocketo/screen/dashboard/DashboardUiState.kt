package com.winphyoethu.pocketo.screen.dashboard

import com.winphyoethu.pocketo.model.expense.Expense
import com.winphyoethu.pocketo.model.monthlyusage.MonthlyExpense

sealed class ExpenseState

data object Loading: ExpenseState()

data class ShowExpense(val expenseList: List<Expense>): ExpenseState()

data object EmptyExpense: ExpenseState()

data class DashboardUiState(
    val expenseState: ExpenseState,
    val accountName: String = "",
    val monthlyExpense: MonthlyExpense? = null
)