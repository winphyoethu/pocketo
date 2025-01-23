package com.winphyoethu.pocketo.screen.createexpense.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.winphyoethu.pocketo.screen.createexpense.CreateExpenseRoute
import kotlinx.serialization.Serializable

@Serializable
data object CreateExpenseScreen

fun NavController.navigateToCreateExpenseScreen(navOptions: NavOptions? = null) {
    navigate(CreateExpenseScreen, navOptions)
}

fun NavGraphBuilder.createExpenseScreen(onBackClick: () -> Unit) {
    composable<CreateExpenseScreen>(deepLinks = listOf(navDeepLink {
        uriPattern = "tayoteapp://pocketo/createexpense"
    })) {
        CreateExpenseRoute(onBackClick = onBackClick)
    }
}