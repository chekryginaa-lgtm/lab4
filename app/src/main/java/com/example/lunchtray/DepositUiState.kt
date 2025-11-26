package com.example.lunchtray

data class DepositUiState(
    val initial: Double = 0.0,
    val rate: Double = 0.0,
    val monthly: Double = 0.0,
    val months: Int = 0,
    val total: Double = 0.0,
    val interestEarned: Double = 0.0
)
