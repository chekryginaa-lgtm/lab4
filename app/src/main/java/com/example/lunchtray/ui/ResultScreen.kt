package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.DepositUiState

@Composable
fun ResultScreen(
    uiState: DepositUiState,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()              // занимаем весь экран
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,          // центрируем по вертикали
        horizontalAlignment = Alignment.CenterHorizontally // центрируем по горизонтали
    ) {
        Text(
            text = "Суммарная информация:",
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Итоговая сумма: ${"%.2f".format(uiState.total)}", fontSize = 24.sp)
        Text("Процентный доход: ${"%.2f".format(uiState.interestEarned)}", fontSize = 24.sp)
        Text("Первоначальный взнос: ${"%.2f".format(uiState.initial)}", fontSize = 24.sp)
        Text("Процентная ставка: ${"%.2f".format(uiState.rate)}", fontSize = 24.sp)
        Text("Ежемесячное пополнение: ${"%.2f".format(uiState.monthly)}", fontSize = 24.sp)
        Text("Период в месяцах: ${uiState.months}", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onReset,
            modifier = Modifier.fillMaxWidth(0.5f) // кнопка по центру и половина ширины экрана
        ) {
            Text("Начать заново", fontSize = 20.sp)
        }
    }
}
