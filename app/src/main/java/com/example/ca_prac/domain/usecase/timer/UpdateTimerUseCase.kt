package com.example.ca_prac.domain.usecase.timer
import com.example.ca_prac.domain.model.TimerType
import com.example.ca_prac.domain.repository.TimerRepository
import javax.inject.Inject

class UpdateTimerUseCase @Inject constructor(
    val repository: TimerRepository
) {
    suspend operator fun invoke(type: TimerType) = repository.updateTimer(type)
}