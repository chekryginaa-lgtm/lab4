package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.lunchtray.DepositUiState

@Composable
fun ResultScreen(
    uiState: DepositUiState,
    onReset: () -> Unit
) {
    Column {
        Text("Суммарная информация:")
        Text("Итоговая сумма: ${"%.2f".format(uiState.total)}")
        Text("Процентный доход: ${"%.2f".format(uiState.interestEarned)}")
        Text("Первоначальный взнос: ${"%.2f".format(uiState.initial)}")
        Text("Процентная ставка: ${"%.2f".format(uiState.rate)}")
        Text("Ежемесячное пополнение: ${"%.2f".format(uiState.monthly)}")
        Text("Период в месяцах: ${uiState.months}")

        Button(onClick = onReset) {
            Text("Начать заново")
        }
    }
}
