package com.winphyoethu.pocketo.domain.usecase

import com.winphyoethu.pocketo.data.repository.AccountRepository
import com.winphyoethu.pocketo.data.repository.MonthlyExpenseRepository
import javax.inject.Inject

class CreateMonthlyExpenseUseCase @Inject constructor(
    val accountRepository: AccountRepository,
    val repository: MonthlyExpenseRepository
) {

    fun execute() {
        // TODO :: when create monthly expense, create one if there isn't any.

    }

}