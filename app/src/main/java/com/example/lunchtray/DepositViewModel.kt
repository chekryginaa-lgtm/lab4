package com.example.lunchtray

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.text.NumberFormat
import kotlin.math.round

class DepositViewModel : ViewModel() {

    // вводимые поля (строки, чтобы TextField работал удобно)
    var initialAmount by mutableStateOf("")
    var interestRate by mutableStateOf("")  // годовой процент в %
    var monthlyAdd by mutableStateOf("")
    var months by mutableStateOf("")

    // результаты
    var finalAmount by mutableStateOf(0.0)
    var incomeAmount by mutableStateOf(0.0)

    // расчет — вызываем после ввода всех полей
    fun calculate() {
        val P = initialAmount.toDoubleOrNull() ?: 0.0
        val annualPercent = interestRate.toDoubleOrNull() ?: 0.0
        val monthlyPercent = annualPercent / 100.0 / 12.0
        val A = monthlyAdd.toDoubleOrNull() ?: 0.0
        val n = months.toIntOrNull() ?: 0

        var sum = P
        // простой подход: на каждую итерацию добавляем A, затем начисляем процент
        repeat(n) {
            sum += A
            sum *= (1 + monthlyPercent)
        }

        // Если n == 0, просто начисляем 0, final = P
        finalAmount = round(sum * 100) / 100.0  // округлим до 2 знаков
        incomeAmount = round((finalAmount - P - A * n) * 100) / 100.0
    }

    fun reset() {
        initialAmount = ""
        interestRate = ""
        monthlyAdd = ""
        months = ""
        finalAmount = 0.0
        incomeAmount = 0.0
    }

    // Удобная строка в валютном формате
    fun formatCurrency(value: Double): String =
        NumberFormat.getCurrencyInstance().format(value)
}
