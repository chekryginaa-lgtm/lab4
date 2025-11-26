package com.example.lunchtray

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.ui.StartScreen
import com.example.lunchtray.ui.Step1Screen
import com.example.lunchtray.ui.Step2Screen
import com.example.lunchtray.ui.ResultScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepositApp() {
    val navController = rememberNavController()
    val viewModel: DepositViewModel = viewModel()

    Scaffold { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = DepositScreen.Start.name,
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {

            composable(DepositScreen.Start.name) {
                StartScreen(
                    onStart = {
                        navController.navigate(DepositScreen.Step1.name)
                    }
                )
            }

            composable(DepositScreen.Step1.name) {
                Step1Screen(
                    onNext = {
                        navController.navigate(DepositScreen.Step2.name)
                    }
                )
            }

            composable(DepositScreen.Step2.name) {
                Step2Screen(
                    onNext = {
                        viewModel.calculate()
                        navController.navigate(DepositScreen.Result.name)
                    }
                )
            }

            composable(DepositScreen.Result.name) {
                ResultScreen(
                    onRestart = {
                        viewModel.reset()
                        navController.popBackStack(DepositScreen.Start.name, false)
                    }
                )
            }
        }
    }
}
