package com.winphyoethu.pocketo.domain.usecase

import com.winphyoethu.pocketo.common.result.ExpenseErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.data.repository.ExpenseRepository
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepository
import java.util.Calendar
import javax.inject.Inject

class UpdateMonthlyExpenseUseCase @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val monthlyExpenseRepository: MonthlyExpenseRepository
) {

    suspend operator fun invoke(
        accountId: Int,
        description: String,
        categoryId: Int,
        currencyId: Int,
        amount: Double
    ): PocketoResult<Int> {
        val expenseResult = expenseRepository.createExpense(
            accountId = accountId,
            description = description,
            categoryId = categoryId,
            currencyId = currencyId,
            amount = amount
        )

        return if (expenseResult is PocketoResult.Success) {
            PocketoResult.Success(1L)
            val calendar = Calendar.getInstance()
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            calendar.set(year, month, 1, 0, 0, 0)

            val monthlyExpenseResult =
                monthlyExpenseRepository.getMonthlyExpense(calendar.timeInMillis, accountId)

            if (monthlyExpenseResult is PocketoResult.Success) {
                val remainingBalance = monthlyExpenseResult.data!!.remainingBalance - amount
                val updateMonthlyExpenseResult = monthlyExpenseRepository.updateMonthlyExpense(
                    id = monthlyExpenseResult.data!!.id,
                    remainingBalance = remainingBalance
                )

                updateMonthlyExpenseResult
            } else {
                PocketoResult.Error(ExpenseErrorCode.ExpenseCreateFail)
            }
        } else {
            PocketoResult.Error(ExpenseErrorCode.ExpenseCreateFail)
        }
    }

}