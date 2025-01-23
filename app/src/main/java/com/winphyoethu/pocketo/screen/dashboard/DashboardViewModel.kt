package com.winphyoethu.pocketo.screen.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.pocketo.data.repository.ExpenseRepository
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepository
import com.winphyoethu.pocketo.datastore.AccountInfoDataSource
import com.winphyoethu.pocketo.model.monthlyusage.MonthlyExpense
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val monthlyExpenseRepository: MonthlyExpenseRepository,
    private val accountInfoDataSource: AccountInfoDataSource
) : ViewModel() {

    private var _dashboardUiState = MutableStateFlow(DashboardUiState(expenseState = Loading))

    private val dashboardUiState = _dashboardUiState.asStateFlow()
        .onStart { }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = DashboardUiState(expenseState = Loading)
        )

    val accountName = accountInfoDataSource.accountData.map { it.name }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = ""
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private var _expenseListState = accountInfoDataSource.accountData.flatMapLatest {
        expenseRepository.getExpense(0, Calendar.getInstance().timeInMillis, it.id)
    }

    val expenseListState = _expenseListState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = listOf()
    )

    private var _monthlyExpenseUiState =
        accountInfoDataSource.accountData.map {
            MonthlyExpense(0, 0, 0.0, 0.0, "0")
//            monthlyExpenseRepository.getMonthlyExpenseFlow(0, account.id).map { monthlyExpense ->
//                monthlyExpense.copy(accountName = account.name)
//            }
        }
//        accountInfoDataSource.accountData.flatMapLatest { account ->
//            MonthlyExpense(0, 0, 0.0, 0.0, "0")
//            monthlyExpenseRepository.getMonthlyExpenseFlow(0, account.id).map { monthlyExpense ->
//                monthlyExpense.copy(accountName = account.name)
//            }
//        }

    val monthlyBalanceUiState = _monthlyExpenseUiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MonthlyExpense(0, 0, 0.0, 0.0, "0")
    )

}