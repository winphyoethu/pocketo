package com.winphyoethu.pocketo.screen.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.winphyoethu.pocketo.screen.dashboard.DashboardRoute
import kotlinx.serialization.Serializable

@Serializable
data object DashboardScreen

fun NavController.navigateToDashboard(navOptions: NavOptions? = null) {
    navigate(DashboardScreen, navOptions = navOptions)
}

fun NavGraphBuilder.dashboardScreen(onCreateExpenseClick: () -> Unit) {
    composable<DashboardScreen>(deepLinks = listOf(navDeepLink {
        uriPattern = "tayoteapp://pocketo/dashboard"
    })) {
        DashboardRoute(onCreateExpenseClick = onCreateExpenseClick)
    }
}