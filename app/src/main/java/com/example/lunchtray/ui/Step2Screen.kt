package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.DepositUiState
import androidx.compose.ui.text.TextStyle

@Composable
fun Step2Screen(
    uiState: DepositUiState,
    onMonthlyChange: (Double) -> Unit,
    onMonthsChange: (Int) -> Unit,
    onNext: () -> Unit
) {
    var monthlyText by remember { mutableStateOf(if (uiState.monthly == 0.0) "" else uiState.monthly.toString()) }
    var monthsText by remember { mutableStateOf(if (uiState.months == 0) "" else uiState.months.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,          // по центру экрана вертикально
        horizontalAlignment = Alignment.CenterHorizontally // по центру экрана горизонтально
    ) {
        // Ежемесячное пополнение
        TextField(
            value = monthlyText,
            onValueChange = {
                monthlyText = it
                onMonthlyChange(it.toDoubleOrNull() ?: 0.0)
            },
            label = { Text("Ежемесячное пополнение") },
            textStyle = TextStyle(
                fontSize = 24.sp,       // крупный текст
                textAlign = TextAlign.Center // текст по центру
            ),
            modifier = Modifier.fillMaxWidth(0.7f) // ширина поля 70% от экрана
        )

        Spacer(modifier = Modifier.height(16.dp)) // отступ между полями

        // Период в месяцах
        TextField(
            value = monthsText,
            onValueChange = {
                monthsText = it
                onMonthsChange(it.toIntOrNull() ?: 0)
            },
            label = { Text("Период в месяцах") },
            textStyle = TextStyle(
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNext, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text("Рассчитать", fontSize = 20.sp)
        }
    }
}
