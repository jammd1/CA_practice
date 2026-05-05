package com.example.ca_prac.domain.usecase.timer

import com.example.ca_prac.domain.model.TimerEntity
import com.example.ca_prac.domain.repository.TimerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTimerFlowUseCase @Inject constructor(
    private val repository: TimerRepository
) {
    operator fun invoke(): Flow<TimerEntity> = repository.getTimerFlow()
}