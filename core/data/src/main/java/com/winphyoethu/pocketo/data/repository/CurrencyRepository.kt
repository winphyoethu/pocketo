package com.winphyoethu.pocketo.data.repository

import com.winphyoethu.pocketo.common.result.CurrencyErrorCode
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.database.datasource.CurrencyLocalDatasource
import com.winphyoethu.pocketo.model.currency.Currency
import javax.inject.Inject

/**
 * API for CurrencyRepository
 *
 * @see CurrencyRepositoryImpl for actual implementation
 */

interface CurrencyRepository {

    /**
     * Get AllCurrencies
     *
     * @return PocketoResult [PocketoResult] which type is List of Currency [Currency]
     */
    suspend fun getAllCurrencies(): PocketoResult<List<Currency>>

}

/**
 * Implementation of CurrencyRepository [CurrencyRepository]
 * @see CurrencyRepository
 *
 * @param datasource Local Data Source for Currency [CurrencyLocalDatasource]
 */
class CurrencyRepositoryImpl @Inject constructor(val datasource: CurrencyLocalDatasource) : CurrencyRepository {

    override suspend fun getAllCurrencies(): PocketoResult<List<Currency>> {
        return try {
            val result = datasource.getAllCurrencies()

            return PocketoResult.Success(
                result.map {
                    Currency(
                        id = it.id,
                        name = it.name,
                        code = it.code,
                        symbol = it.symbol
                    )
                }.sortedBy { it.name }
            )
        } catch (e: Exception) {
            PocketoResult.Error(CurrencyErrorCode.CurrencyRetrieveFail)
        }
    }

}