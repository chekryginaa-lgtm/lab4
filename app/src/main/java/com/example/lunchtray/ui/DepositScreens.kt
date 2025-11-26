package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lunchtray.DepositViewModel

@Composable
fun StartScreen(onStart: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onStart, modifier = Modifier.widthIn(min = 250.dp)) {
            Text("Рассчитать вклад")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Step1Screen(
    onNext: () -> Unit,
    viewModel: DepositViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        OutlinedTextField(
            value = viewModel.initialAmount,
            onValueChange = { viewModel.initialAmount = it },
            label = { Text("Первоначальный взнос") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.interestRate,
            onValueChange = { viewModel.interestRate = it },
            label = { Text("Процентная ставка (годовых %)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNext, modifier = Modifier.align(Alignment.End)) {
            Text("Далее")
        }
    }
}

@Composable
fun Step2Screen(
    onNext: () -> Unit,
    viewModel: DepositViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize()) {

        OutlinedTextField(
            value = viewModel.monthlyAdd,
            onValueChange = { viewModel.monthlyAdd = it },
            label = { Text("Ежемесячное пополнение") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = viewModel.months,
            onValueChange = { viewModel.months = it },
            label = { Text("Период (в месяцах)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNext, modifier = Modifier.align(Alignment.End)) {
            Text("Рассчитать")
        }
    }
}

@Composable
fun ResultScreen(
    onRestart: () -> Unit,
    viewModel: DepositViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
        .fillMaxSize()) {

        Text("Итоговая сумма: ${viewModel.formatCurrency(viewModel.finalAmount)}",
            style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Процентный доход: ${viewModel.formatCurrency(viewModel.incomeAmount)}",
            style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(12.dp))
        Divider()
        Spacer(modifier = Modifier.height(12.dp))

        Text("Параметры расчёта:", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(6.dp))
        Text("Первоначальный взнос: ${viewModel.initialAmount}")
        Text("Годовая ставка: ${viewModel.interestRate}%")
        Text("Ежемесячное пополнение: ${viewModel.monthlyAdd}")
        Text("Срок (мес): ${viewModel.months}")

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onRestart, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("На начало")
        }
    }
}
