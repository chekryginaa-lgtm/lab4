package com.example.lunchtray

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.Step1Screen
import com.example.lunchtray.ui.Step2Screen
import com.example.lunchtray.ui.ResultScreen
import com.example.lunchtray.ui.StartScreen

@Composable
fun DepositApp() {
    val navController = rememberNavController()
    val viewModel: DepositViewModel = viewModel()

    NavHost(navController = navController, startDestination = DepositScreen.Start.name) {
        composable(DepositScreen.Start.name) {
            StartScreen(onStart = { navController.navigate(DepositScreen.Step1.name) })
        }
        composable(DepositScreen.Step1.name) {
            Step1Screen(
                uiState = viewModel.uiState.value,
                onInitialChange = { viewModel.updateInitial(it) },
                onRateChange = { viewModel.updateRate(it) },
                onNext = { navController.navigate(DepositScreen.Step2.name) }
            )
        }
        composable(DepositScreen.Step2.name) {
            Step2Screen(
                uiState = viewModel.uiState.value,
                onMonthlyChange = { viewModel.updateMonthly(it) },
                onMonthsChange = { viewModel.updateMonths(it) },
                onNext = {
                    viewModel.calculate()
                    navController.navigate(DepositScreen.Result.name)
                }
            )
        }
        composable(DepositScreen.Result.name) {
            ResultScreen(
                uiState = viewModel.uiState.value,
                onReset = {
                    viewModel.reset()
                    navController.navigate(DepositScreen.Start.name) {
                        popUpTo(DepositScreen.Start.name) { inclusive = true }
                    }
                }
            )
        }
    }
}
