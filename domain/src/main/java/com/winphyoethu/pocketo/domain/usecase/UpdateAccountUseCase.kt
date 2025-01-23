package com.winphyoethu.pocketo.domain.usecase

import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.data.repository.AccountRepository
import com.winphyoethu.pocketo.model.account.Account
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.yield
import java.util.concurrent.locks.ReentrantReadWriteLock
import javax.inject.Inject
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

class UpdateAccountUseCase @Inject constructor(val repository: AccountRepository) {

    val scope = CoroutineScope(Dispatchers.IO)

    suspend fun execute(): PocketoResult<List<Account>> {
        val accountResult = repository.getAccountById(0)

//        val allAccountResult = repository.updateAccount(accountResult.hashCode(), "", 1000.0, "AUD")

        val reentrantLock = ReentrantReadWriteLock()

        return repository.getAllAccount()
        // TODO :: when update an account income, need to update not only account income, but also income in current monthly expense.
    }

    suspend fun gg() {
        curriedAdd(3)(4)
    }

    val curriedAdd: (Int) -> (Int) -> Int = { x -> { y -> x + y } }

}