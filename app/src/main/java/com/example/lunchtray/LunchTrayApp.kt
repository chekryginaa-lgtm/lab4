package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.R
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.*

/**
 * Перечисление всех экранов приложения
 */
enum class LunchTrayScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Entree(title = R.string.choose_entree),
    SideDish(title = R.string.choose_side_dish),
    Accompaniment(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout)
}

/**
 * Главный composable приложения Lunch Tray
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp() {

    // Контроллер навигации
    val navController = rememberNavController()

    // ViewModel для хранения состояния заказа
    val viewModel: OrderViewModel = viewModel()

    // Текущее состояние навигации
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = LunchTrayScreen.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreen.Start.name
    )

    // UI состояние заказа (обновляется через ViewModel)
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            LunchTrayAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = LunchTrayScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {

            // ---------- ЭКРАН СТАРТА ----------
            composable(route = LunchTrayScreen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = {
                        navController.navigate(LunchTrayScreen.Entree.name)
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            // ---------- ЭКРАН ВЫБОРА ОСНОВНОГО БЛЮДА ----------
            composable(route = LunchTrayScreen.Entree.name) {
                EntreeMenuScreen(
                    options = DataSource.entreeMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.SideDish.name)
                    },
                    onSelectionChanged = { viewModel.updateEntree(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            // ---------- ЭКРАН ВЫБОРА ГАРНИРА ----------
            composable(route = LunchTrayScreen.SideDish.name) {
                SideDishMenuScreen(
                    options = DataSource.sideDishMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Accompaniment.name)
                    },
                    onSelectionChanged = { viewModel.updateSideDish(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            // ---------- ЭКРАН ДОПОЛНЕНИЙ ----------
            composable(route = LunchTrayScreen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = DataSource.accompanimentMenuItems,
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onNextButtonClicked = {
                        navController.navigate(LunchTrayScreen.Checkout.name)
                    },
                    onSelectionChanged = { viewModel.updateAccompaniment(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }

            // ---------- ЭКРАН ОПЛАТЫ ----------
            composable(route = LunchTrayScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    onCancelButtonClicked = {
                        viewModel.resetOrder()
                        navController.popBackStack(LunchTrayScreen.Start.name, false)
                    },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
        }
    }
}
