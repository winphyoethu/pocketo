package com.winphyoethu.pocketo.domain.usecase

import com.winphyoethu.pocketo.common.result.AccountErrorCode
import com.winphyoethu.pocketo.common.result.MonthlyExpenseErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.data.repository.AccountRepository
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepository
import com.winphyoethu.pocketo.datastore.AccountInfoDataSource
import com.winphyoethu.pocketo.model.account.Account
import com.winphyoethu.pocketo.model.currency.Currency
import javax.inject.Inject

class CreateAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val monthlyExpenseRepository: MonthlyExpenseRepository,
    private val accountInfoDataSource: AccountInfoDataSource
) {

    suspend operator fun invoke(
        name: String,
        profession: String,
        income: Double,
        currency: Currency
    ): PocketoResult<Long> {
        return try {
            val accountCreateResult = accountRepository.createAccount(
                name = name,
                profession = profession,
                income = income,
                currencyId = currency.id
            )

            if (accountCreateResult is PocketoResult.Success) {
                val monthlyExpenseCreateResult = monthlyExpenseRepository.createMonthlyExpense(
                    accountId = accountCreateResult.data.toInt(),
                    balance = income,
                    currencyId = currency.id
                )

                if (monthlyExpenseCreateResult is PocketoResult.Success) {

                    accountInfoDataSource.setAccountInfo(
                        Account(
                            id = monthlyExpenseCreateResult.data.toInt(),
                            name = name,
                            income = income,
                            currencyId = currency.id,
                            currencyCode = currency.code,
                            currencySymbol = currency.symbol
                        )
                    )

                    PocketoResult.Success(monthlyExpenseCreateResult.data)
                } else {
                    PocketoResult.Error(MonthlyExpenseErrorCode.MonthlyExpenseCreateFail)
                }
            } else {
                PocketoResult.Error(AccountErrorCode.AccountCreateFail)
            }
        } catch (e: Exception) {
            PocketoResult.Error(AccountErrorCode.AccountCreateFail)
        }
    }

}