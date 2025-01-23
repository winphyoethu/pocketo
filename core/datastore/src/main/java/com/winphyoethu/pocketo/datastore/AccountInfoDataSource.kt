package com.winphyoethu.pocketo.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.winphyoethu.pocketo.AccountInfo
import com.winphyoethu.pocketo.copy
import com.winphyoethu.pocketo.model.account.Account
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AccountInfoDataSource @Inject constructor(
    private val accountInfo: DataStore<AccountInfo>
) {

    val accountData = accountInfo.data.map {
        Log.d("accountInfoDataSource :: ", it.toString())
        Account(
            id = it.accountId,
            name = it.accountName,
            income = it.income,
            currencyId = it.currencyId,
            currencyCode = it.currencyCode,
            currencySymbol = it.currencySymbol
        )
    }

    suspend fun setAccountInfo(account: Account) {
        try {
            accountInfo.updateData {
                it.copy {
                    accountId = account.id
                    accountName = account.name
                    income = account.income
                    currencyId = account.currencyId
                    currencyCode = account.currencyCode
                    currencySymbol = account.currencySymbol
                }
            }
        } catch (e: IOException) {
            Log.e("PocketoAccountInfoDataSource :: ", e.toString())
        }
    }

}