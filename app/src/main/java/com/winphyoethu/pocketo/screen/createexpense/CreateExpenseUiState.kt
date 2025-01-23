package com.winphyoethu.pocketo.screen.createexpense

import com.winphyoethu.pocketo.model.category.Category

sealed class CreateExpenseState

data object Initial : CreateExpenseState()

data class ShowCategoryBottomSheet(val categoryList: List<Category>) : CreateExpenseState()

data class CreateExpenseUiState(
    val state: CreateExpenseState,
    val description: String = "",
    val amount: String = "0.0",
    val category: Category? = null,
    val currencyCode: String = "",
    val currencySymbol: String = ""
)