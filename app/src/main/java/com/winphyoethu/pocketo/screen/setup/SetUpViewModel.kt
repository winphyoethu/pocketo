package com.winphyoethu.pocketo.screen.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.pocketo.R
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.data.repository.CurrencyRepository
import com.winphyoethu.pocketo.domain.usecase.CreateAccountUseCase
import com.winphyoethu.pocketo.model.currency.Currency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetUpViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val createAccountUseCase: CreateAccountUseCase,
) : ViewModel() {

    private val currencyList: MutableList<Currency> = ArrayList()

    private val _navigationEvent = MutableSharedFlow<Unit>()
    val navigationEvent = _navigationEvent.asSharedFlow().shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L)
    )

    private val _errorEvent = MutableSharedFlow<Int>()
    val errorEvent = _errorEvent.asSharedFlow().shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L)
    )

    private var _setUpUiState = MutableStateFlow(SetUpUiState(FirstStep))

    val setUpUiState = _setUpUiState.asStateFlow()
        .onStart {
            getAllCurrencies()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = SetUpUiState(FirstStep)
        )

    fun setFullName(fullName: String) {
        viewModelScope.launch {
            _setUpUiState.emit(setUpUiState.value.copy(fullName = fullName))
        }
    }

    fun setProfession(profession: String) {
        viewModelScope.launch {
            _setUpUiState.emit(setUpUiState.value.copy(profession = profession))
        }
    }

    fun setIncome(income: String) {
        viewModelScope.launch {
            _setUpUiState.emit(setUpUiState.value.copy(income = income))
        }
    }

    fun setCurrency(currency: Currency? = null) {
        viewModelScope.launch {
            _setUpUiState.emit(
                setUpUiState.value.copy(
                    state = FinalStep,
                    currency = currency ?: currencyList[0],
                    showCurrencyBottomSheet = false,
                    currencyList = null
                )
            )
        }
    }

    fun changeState(backState: Boolean = false) {
        viewModelScope.launch {
            if (backState) {
                _setUpUiState.emit(setUpUiState.value.copy(state = FirstStep))
            } else {
                _setUpUiState.emit(setUpUiState.value.copy(state = FinalStep))
            }
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            val result = createAccountUseCase(
                name = setUpUiState.value.fullName,
                income = setUpUiState.value.income.toDouble(),
                profession = setUpUiState.value.profession,
                currency = setUpUiState.value.currency!!
            )

            if (result is PocketoResult.Success) {
                _navigationEvent.emit(Unit)
            } else if (result is PocketoResult.Error) {
                _errorEvent.emit(R.string.set_up_error)
            }
        }
    }

    fun showCurrenciesBottomSheet() {
        viewModelScope.launch {
            _setUpUiState.emit(
                setUpUiState.value.copy(
                    showCurrencyBottomSheet = true,
                    currencyList = currencyList
                )
            )
        }
    }

    private fun getAllCurrencies() {
        viewModelScope.launch {
            val result = currencyRepository.getAllCurrencies()

            if (result is PocketoResult.Success) {
                currencyList.clear()
                currencyList.addAll(result.data)
                _setUpUiState.emit(setUpUiState.value.copy(currency = currencyList[0]))
            }
        }
    }

}