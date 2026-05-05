package com.example.ca_prac.presentation.timer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ca_prac.domain.model.TimerType
import com.example.ca_prac.domain.usecase.timer.GetTimerFlowUseCase
import com.example.ca_prac.domain.usecase.timer.PauseTimerUseCase
import com.example.ca_prac.domain.usecase.timer.ResetTimerUseCase
import com.example.ca_prac.domain.usecase.timer.StartTimerUseCase
import com.example.ca_prac.domain.usecase.timer.UpdateTimerTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TimerViewModel @Inject constructor(
    private val getTimerFlowUseCase: GetTimerFlowUseCase,
    private val startTimerUseCase: StartTimerUseCase,
    private val pauseTimerUseCase: PauseTimerUseCase,
    private val resetTimerUseCase: ResetTimerUseCase,
    private val updateTimerTypeUseCase: UpdateTimerTypeUseCase
): ViewModel() {

    val uiState: StateFlow<TimerUiState> = getTimerFlowUseCase().map {
        timer ->
        TimerUiState(
            formattedTime = formatTime(timer.remainingMillis),
            isRunning = timer.isRunning,
            type = timer.type
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = TimerUiState()
    )

    fun startTimer() = viewModelScope.launch { startTimerUseCase() }

    fun pauseTimer() = viewModelScope.launch { pauseTimerUseCase() }

    fun resetTimer() = viewModelScope.launch { resetTimerUseCase() }

    fun updateTimerType(type: TimerType) = viewModelScope.launch { updateTimerTypeUseCase(type) }


    private fun formatTime(mills: Long): String {
        val minutes = (mills / 1000) / 60
        val seconds = (mills / 1000) % 60
        return "%02d:%02d".format(minutes, seconds)
    }

}