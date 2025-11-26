package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import com.example.lunchtray.DepositUiState

@Composable
fun Step2Screen(
    uiState: DepositUiState,
    onMonthlyChange: (Double) -> Unit,
    onMonthsChange: (Int) -> Unit,
    onNext: () -> Unit
) {
    var monthlyText by remember { mutableStateOf(uiState.monthly.toString()) }
    var monthsText by remember { mutableStateOf(uiState.months.toString()) }

    Column {
        TextField(
            value = monthlyText,
            onValueChange = {
                monthlyText = it
                onMonthlyChange(it.toDoubleOrNull() ?: 0.0)
            },
            label = { Text("Ежемесячное пополнение") }
        )

        TextField(
            value = monthsText,
            onValueChange = {
                monthsText = it
                onMonthsChange(it.toIntOrNull() ?: 0)
            },
            label = { Text("Период в месяцах") }
        )

        Button(onClick = onNext) {
            Text("Рассчитать")
        }
    }
}
