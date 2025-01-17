package com.example.feature.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.feature.ui.exchange.ExchangeScreen
import com.example.feature.ui.exchange.ExchangeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = "exchange") {

        composable("exchange") {
            val viewModel = koinViewModel<ExchangeViewModel>()
            ExchangeScreen(viewModel)
        }
    }
}