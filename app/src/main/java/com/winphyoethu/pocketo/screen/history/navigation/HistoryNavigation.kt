package com.winphyoethu.pocketo.screen.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.winphyoethu.pocketo.screen.history.History
import kotlinx.serialization.Serializable

@Serializable
data object HistoryScreen

fun NavController.navigateToHistoryScreen(navOptions: NavOptions? = null) {
    navigate(HistoryScreen, navOptions)
}

fun NavGraphBuilder.historyScreen() {
    composable<HistoryScreen>(deepLinks = listOf(navDeepLink {
        uriPattern = "tayoteapp://pocketo/history"
    })) {
        History()
    }
}