package com.winphyoethu.pocketo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.winphyoethu.pocketo.designsystem.ui.theme.PocketoTheme
import com.winphyoethu.pocketo.screen.createexpense.navigation.createExpenseScreen
import com.winphyoethu.pocketo.screen.createexpense.navigation.navigateToCreateExpenseScreen
import com.winphyoethu.pocketo.screen.dashboard.navigation.DashboardScreen
import com.winphyoethu.pocketo.screen.dashboard.navigation.dashboardScreen
import com.winphyoethu.pocketo.screen.dashboard.navigation.navigateToDashboard
import com.winphyoethu.pocketo.screen.history.navigation.historyScreen
import com.winphyoethu.pocketo.screen.setup.navigation.SetUpScreen
import com.winphyoethu.pocketo.screen.setup.navigation.setUpScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()
        var keepSplashScreen = true
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }

//        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            val userData by viewModel.accountInfo.collectAsStateWithLifecycle()

            if (userData != null) {
                keepSplashScreen = false
            }

            PocketoTheme(dynamicColor = false) {
                if (userData == null) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(R.drawable.bg),
                            contentDescription = "Splash",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = if (userData!!.id == 0) SetUpScreen else DashboardScreen,
                    ) {
                        setUpScreen(onAccountSetUpFinish = {
                            navController.navigateToDashboard(navOptions = navOptions {
                                popUpTo(0)
                            })
                        })
                        dashboardScreen(onCreateExpenseClick = {
                            navController.navigateToCreateExpenseScreen()
                        })
                        createExpenseScreen(onBackClick = {
                            navController.popBackStack()
                        })
                        historyScreen()
                    }
                }
            }
        }
    }

}