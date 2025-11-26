package com.example.lunchtray.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartScreen(onStart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,          // по центру вертикально
        horizontalAlignment = Alignment.CenterHorizontally // по центру горизонтально
    ) {
        Text(
            text = "Рассчитать вклад",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onStart, modifier = Modifier.fillMaxWidth(0.5f)) {
            Text("Начать", fontSize = 20.sp)
        }
    }
}
