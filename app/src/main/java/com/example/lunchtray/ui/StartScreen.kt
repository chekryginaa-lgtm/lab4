package com.example.lunchtray.ui

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun StartScreen(onStart: () -> Unit) {
    Button(onClick = onStart) {
        Text("Рассчитать вклад")
    }
}
