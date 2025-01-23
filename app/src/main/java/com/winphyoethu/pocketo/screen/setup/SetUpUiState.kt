package com.winphyoethu.pocketo.screen.setup

import com.winphyoethu.pocketo.model.currency.Currency

sealed class SetUpState

data object FirstStep : SetUpState()

data object FinalStep : SetUpState()

data class SetUpUiState(
    val state: SetUpState = FirstStep,
    val fullName: String = "",
    val profession: String = "",
    val income: String = "",
    val currency: Currency? = null,
    val showCurrencyBottomSheet: Boolean = false,
    val currencyList: List<Currency>? = null
)