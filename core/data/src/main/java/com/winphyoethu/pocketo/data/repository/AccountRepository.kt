package com.winphyoethu.pocketo.data.repository

import com.winphyoethu.pocketo.common.result.AccountErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.database.datasource.AccountLocalDatasource
import com.winphyoethu.pocketo.database.entity.AccountEntity
import com.winphyoethu.pocketo.model.account.Account
import java.util.Calendar
import javax.inject.Inject

/**
 * API for Account Repository
 *
 * @see AccountRepositoryImpl for actual implementation
 */
interface AccountRepository {

    /**
     * create account for the user
     *
     * @param name full name of the account
     * @param profession profession of the account and OPTIONAL
     * @param income income of the account to be recorded
     * @param currencyId id of currency used for this account
     *
     * @return PocketoResult [PocketoResult] which type is Long(id of a created account)
     */
    suspend fun createAccount(
        name: String,
        profession: String,
        income: Double,
        currencyId: Int
    ): PocketoResult<Long>

    /**
     * update existing account for the user
     *
     * @param id id of the account
     * @param name full name of the account
     * @param profession profession of the account and OPTIONAL
     * @param income income of the account to be recorded
     * @param currencyId id of currency used for this account
     *
     * @return PocketoResult [PocketoResult] which type is Integer(number of row affected)
     */
    suspend fun updateAccount(
        id: Int,
        name: String,
        profession: String,
        income: Double,
        currencyId: Int
    ): PocketoResult<Int>

    /**
     * retrieve all the available account
     *
     * @return PocketoResult [PocketoResult] which type is List of Account [Account]
     */
    suspend fun getAllAccount(): PocketoResult<List<Account>>

    /**
     * retrieve all the available account
     * @param id id of an account to be retrieved
     * @return PocketoResult [PocketoResult] of Account [Account]
     */
    suspend fun getAccountById(id: Int): PocketoResult<Account>

}

/**
 * Implementation of AccountRepository [AccountRepository]
 * @see AccountRepository
 *
 * @param datasource Local Data Source of Account [AccountLocalDatasource]
 */
class AccountRepositoryImpl @Inject constructor(val datasource: AccountLocalDatasource) :
    AccountRepository {

    override suspend fun createAccount(
        name: String,
        profession: String,
        income: Double,
        currencyId: Int
    ): PocketoResult<Long> {
        val result = datasource.createAccount(
            AccountEntity(
                name = name,
                profession = profession,
                income = income,
                currencyId = currencyId,
                createdAt = Calendar.getInstance().timeInMillis,
                updatedAt = Calendar.getInstance().timeInMillis
            )
        )

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(AccountErrorCode.AccountCreateFail)
        }
    }

    override suspend fun updateAccount(
        id: Int,
        name: String,
        profession: String,
        income: Double,
        currencyId: Int
    ): PocketoResult<Int> {
        val result = datasource.updateAccount(
            AccountEntity(
                id = id,
                name = name,
                profession = profession,
                income = income,
                currencyId = currencyId,
                updatedAt = Calendar.getInstance().timeInMillis
            )
        )

        return if (result > 0) {
            PocketoResult.Success(result)
        } else {
            PocketoResult.Error(AccountErrorCode.AccountUpdateFail)
        }
    }

    override suspend fun getAllAccount(): PocketoResult<List<Account>> {
        val result = datasource.getAllAccount()

        return if (result.isNotEmpty()) {
            PocketoResult.Success(result.map {
                Account(
                    id = it.account.id,
                    name = it.account.name,
                    income = it.account.income,
                    currencyId = it.currency.id,
                    currencyCode = it.currency.code,
                    currencySymbol = it.currency.symbol
                )
            })
        } else {
            PocketoResult.Error(AccountErrorCode.AccountRetrieveFail)
        }
    }

    override suspend fun getAccountById(id: Int): PocketoResult<Account> {
        val result = datasource.getAccountById(id)

        return if (result != null) {
            PocketoResult.Success(
                Account(
                    id = result.account.id,
                    name = result.account.name,
                    income = result.account.income,
                    currencyId = result.currency.id,
                    currencyCode = result.currency.code,
                    currencySymbol = result.currency.symbol
                )
            )
        } else {
            PocketoResult.Error(AccountErrorCode.AccountRetrieveFail)
        }
    }

}