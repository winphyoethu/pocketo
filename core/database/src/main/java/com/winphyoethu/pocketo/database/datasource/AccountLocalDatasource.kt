package com.winphyoethu.pocketo.database.datasource

import com.winphyoethu.pocketo.database.db.AccountDao
import com.winphyoethu.pocketo.database.entity.AccountEntity
import com.winphyoethu.pocketo.database.entity.AccountFullInfo
import javax.inject.Inject

interface AccountLocalDatasource {

    suspend fun createAccount(accountEntity: AccountEntity): Long

    suspend fun getAllAccount(): List<AccountFullInfo>

    suspend fun getAccountById(id: Int): AccountFullInfo?

    suspend fun updateAccount(accountEntity: AccountEntity): Int

    suspend fun deleteAccount(id: Int): Int

}

class AccountLocalDatasourceImpl @Inject constructor(val accountDao: AccountDao) :
    AccountLocalDatasource {

    override suspend fun createAccount(accountEntity: AccountEntity): Long {
        return accountDao.createAccount(accountEntity)
    }

    override suspend fun getAllAccount(): List<AccountFullInfo> {
        return accountDao.getAllAccount()
    }

    override suspend fun getAccountById(id: Int): AccountFullInfo? {
        return accountDao.getAccountById(id)
    }

    override suspend fun updateAccount(accountEntity: AccountEntity): Int {
        return accountDao.updateAccount(accountEntity)
    }

    override suspend fun deleteAccount(id: Int): Int {
//        return accountDao.deleteAccount(id)
        return 0
    }

}