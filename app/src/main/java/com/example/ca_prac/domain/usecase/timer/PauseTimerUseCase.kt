package com.example.ca_prac.domain.usecase.timer

import com.example.ca_prac.domain.repository.TimerRepository
import javax.inject.Inject

class PauseTimerUseCase @Inject constructor(
    private val repository: TimerRepository
) {
    suspend operator fun invoke() = repository.pauseTimer()
}