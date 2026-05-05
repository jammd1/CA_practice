package com.example.ca_prac.presentation.timer

import com.example.ca_prac.domain.model.TimerType

data class TimerUiState(
    val formattedTime: String = "25:00",
    val isRunning: Boolean = false,
    val type: TimerType = TimerType.WORK
)