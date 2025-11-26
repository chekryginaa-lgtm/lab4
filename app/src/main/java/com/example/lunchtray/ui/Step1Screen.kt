package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.DepositUiState

@Composable
fun Step1Screen(
    uiState: DepositUiState,
    onInitialChange: (Double) -> Unit,
    onRateChange: (Double) -> Unit,
    onNext: () -> Unit
) {
    var initialText by remember { mutableStateOf(if (uiState.initial == 0.0) "" else uiState.initial.toString()) }
    var rateText by remember { mutableStateOf(if (uiState.rate == 0.0) "" else uiState.rate.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = initialText,
            onValueChange = {
                initialText = it
                onInitialChange(it.toDoubleOrNull() ?: 0.0)
            },
            label = { Text("Первоначальный взнос") },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = rateText,
            onValueChange = {
                rateText = it
                onRateChange(it.toDoubleOrNull() ?: 0.0)
            },
            label = { Text("Процентная ставка") },
            textStyle = LocalTextStyle.current.copy(
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNext, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text("Дальше", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}
