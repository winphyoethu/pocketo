package com.winphyoethu.pocketo.database.datasource

import com.winphyoethu.pocketo.database.db.CurrencyDao
import com.winphyoethu.pocketo.database.entity.CurrencyEntity
import javax.inject.Inject

interface CurrencyLocalDatasource {

    suspend fun getAllCurrencies(): List<CurrencyEntity>

}

class CurrencyLocalDatasourceImpl @Inject constructor(val currencyDao: CurrencyDao) :
    CurrencyLocalDatasource {

    override suspend fun getAllCurrencies(): List<CurrencyEntity> {
        return currencyDao.getAllCurrencies()
    }

}

