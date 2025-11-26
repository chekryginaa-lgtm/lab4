package com.example.lunchtray

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DepositViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DepositUiState())
    val uiState: StateFlow<DepositUiState> = _uiState

    fun updateInitial(value: Double) {
        _uiState.value = _uiState.value.copy(initial = value)
    }

    fun updateRate(value: Double) {
        _uiState.value = _uiState.value.copy(rate = value)
    }

    fun updateMonthly(value: Double) {
        _uiState.value = _uiState.value.copy(monthly = value)
    }

    fun updateMonths(value: Int) {
        _uiState.value = _uiState.value.copy(months = value)
    }

    fun calculate() {
        val state = _uiState.value
        var total = state.initial
        for (i in 1..state.months) {
            total += state.monthly
            total += total * (state.rate / 100.0 / 12.0)
        }
        val interestEarned = total - (state.initial + state.monthly * state.months)
        _uiState.value = state.copy(total = total, interestEarned = interestEarned)
    }

    fun reset() {
        _uiState.value = DepositUiState()
    }
}
