package com.winphyoethu.pocketo.screen.createexpense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.winphyoethu.pocketo.R
import com.winphyoethu.pocketo.common.result.PocketoResult
import com.winphyoethu.pocketo.data.repository.CategoryRepository
import com.winphyoethu.pocketo.datastore.AccountInfoDataSource
import com.winphyoethu.pocketo.domain.usecase.UpdateMonthlyExpenseUseCase
import com.winphyoethu.pocketo.model.category.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateExpenseViewModel @Inject constructor(
    private val accountInfoDataSource: AccountInfoDataSource,
    private val categoryRepository: CategoryRepository,
    private val updateMonthlyExpenseUseCase: UpdateMonthlyExpenseUseCase
) : ViewModel() {

    private var _shouldShowStatusMessage = MutableSharedFlow<Int>()

    val shouldShowStatusMessage = _shouldShowStatusMessage
        .shareIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L)
        )

    private var _createExpenseUiState = MutableStateFlow(CreateExpenseUiState(Initial))

    val createExpenseUiState = _createExpenseUiState
        .onStart {
            getCategoryAndCurrency()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CreateExpenseUiState(Initial)
        )

    private var accountId = -1
    private var currencyId = -1
    private val categoryList = mutableListOf<Category>()

    fun listenDescription(description: String) {
        viewModelScope.launch {
            _createExpenseUiState.emit(createExpenseUiState.value.copy(description = description))
        }
    }

    fun listenNumpad(number: String, backSpace: Boolean = false) {
        val currentNumber = createExpenseUiState.value.amount
        val temp = if (backSpace) {
            if (currentNumber.isNotEmpty()) {
                currentNumber.substring(0, currentNumber.length - 1).ifEmpty {
                    "0.0"
                }
            } else {
                "0.0"
            }
        } else {
            if (currentNumber == "0.0") {
                number
            } else {
                currentNumber + number
            }
        }
        viewModelScope.launch(start = CoroutineStart.LAZY) {
            _createExpenseUiState.emit(createExpenseUiState.value.copy(amount = temp))
        }
    }

    private fun getCategoryAndCurrency() {
        viewModelScope.launch {
            val list = categoryRepository.getAllCategory()
            if (list is PocketoResult.Success) {
                categoryList.addAll(list.data)
            }

            accountInfoDataSource.accountData.collect {
                accountId = it.id
                currencyId = it.currencyId
                _createExpenseUiState.emit(
                    createExpenseUiState.value.copy(
                        category = categoryList[0],
                        currencyCode = it.currencyCode,
                        currencySymbol = it.currencySymbol
                    )
                )
            }
        }
    }

    fun showCategoryBottomSheet() {
        viewModelScope.launch(Dispatchers.IO) {
            _createExpenseUiState.emit(
                createExpenseUiState.value.copy(
                    state = ShowCategoryBottomSheet(categoryList)
                )
            )
        }
    }

    fun setOrDismissCategory(category: Category? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            _createExpenseUiState.emit(
                createExpenseUiState.value.copy(
                    state = Initial,
                    category = category ?: categoryList[0]
                )
            )
        }
    }

    fun createExpense() {
        viewModelScope.launch {
            val result = updateMonthlyExpenseUseCase.invoke(
                accountId,
                createExpenseUiState.value.description,
                createExpenseUiState.value.category!!.id,
                currencyId,
                createExpenseUiState.value.amount.toDouble(),
            )
            if (result is PocketoResult.Success) {
                _createExpenseUiState.emit(
                    createExpenseUiState.value.copy(
                        state = Initial,
                        category = categoryList[0],
                        amount = "0.0",
                        description = ""
                    )
                )
                _shouldShowStatusMessage.emit(R.string.create_expense_success)
            } else {
                _shouldShowStatusMessage.emit(R.string.create_expense_error)
            }
        }
    }

}