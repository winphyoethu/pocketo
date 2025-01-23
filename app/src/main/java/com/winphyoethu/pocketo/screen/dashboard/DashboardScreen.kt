package com.winphyoethu.pocketo.screen.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoButton
import com.winphyoethu.pocketo.designsystem.basiccomponent.PocketoSubTitle
import com.winphyoethu.pocketo.designsystem.icon.PocketoIcons
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoAppbar
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoBalanceCard
import com.winphyoethu.pocketo.designsystem.pocketocomponent.PocketoExpenseCard
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.designsystem.ui.theme.largeDp
import com.winphyoethu.pocketo.designsystem.ui.theme.mediumDp
import com.winphyoethu.pocketo.model.expense.Expense
import com.winphyoethu.pocketo.model.monthlyusage.MonthlyExpense
import com.winphyoethu.pocketo.model.monthlyusage.mockMonthlyExpense

@Composable
internal fun DashboardRoute(
    onCreateExpenseClick: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel()
) {

    val monthlyBalanceState by viewModel.monthlyBalanceUiState.collectAsStateWithLifecycle()
    val expenseListState by viewModel.expenseListState.collectAsStateWithLifecycle()
    val accountName by viewModel.accountName.collectAsStateWithLifecycle()

    Dashboard(
        monthlyBalanceState = monthlyBalanceState,
        expenseListState = expenseListState,
        accountName = accountName
    ) {
        onCreateExpenseClick()
    }

}

@Composable
internal fun Dashboard(
    monthlyBalanceState: MonthlyExpense = mockMonthlyExpense,
    expenseListState: List<Expense> = listOf(),
    accountName: String = "",
    onCreateExpenseClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(), floatingActionButton = {
            FloatingActionButton(onClick = {
                onCreateExpenseClick()
            }) {
                Icon(imageVector = PocketoIcons.Add, contentDescription = "Add Expense")
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) { contentPadding ->
        Surface(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(16.dp)
            ) {
                PocketoAppbar(
                    icon = PocketoIcons.Dashboard,
                    iconDescription = "Dashboard",
                    title = "Dashboard",
                    rightText = accountName,
                    rightAction = {

                    })
                Spacer(modifier = Modifier.height(largeDp))

                PocketoBalanceCard(
                    monthlyBalanceState.remainingBalance.toString(),
                    monthlyBalanceState.currencyName,
                    monthlyBalanceState.currencySymbol
                )
                Spacer(modifier = Modifier.height(largeDp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PocketoSubTitle(subtitle = "All Expenses")
                    PocketoButton(text = "View All") {

                    }
                }

                if (expenseListState.isEmpty()) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        PocketoSubTitle(subtitle = "No Expense")
                    }
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(mediumDp),
                        modifier = Modifier.padding(top = mediumDp)
                    ) {
                        items(
                            expenseListState.size,
                            key = { index: Int -> expenseListState[index].id }) {
                            PocketoExpenseCard(expenseListState[it])
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardPreview() {
    PocketoTheme(dynamicColor = false) {
        Dashboard(onCreateExpenseClick = {})
    }
}