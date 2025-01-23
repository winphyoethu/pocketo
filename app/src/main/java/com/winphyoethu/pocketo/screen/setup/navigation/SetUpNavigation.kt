package com.winphyoethu.pocketo.screen.setup.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.winphyoethu.pocketo.screen.setup.SetUpRoute
import kotlinx.serialization.Serializable

@Serializable
data object SetUpScreen

fun NavController.navigateToSetUpScreen(navOptions: NavOptions? = null) {
    navigate(SetUpScreen, navOptions)
}

fun NavGraphBuilder.setUpScreen(onAccountSetUpFinish: () -> Unit) {
    composable<SetUpScreen>(deepLinks = listOf(navDeepLink {
        uriPattern = "tayoteapp://pocketo/setup"
    })) {
        SetUpRoute(onAccountSetUpFinish = onAccountSetUpFinish)
    }
}