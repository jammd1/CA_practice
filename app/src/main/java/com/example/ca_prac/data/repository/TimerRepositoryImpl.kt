package com.example.ca_prac.data.repository

import com.example.ca_prac.domain.model.TimerEntity
import com.example.ca_prac.domain.model.TimerType
import com.example.ca_prac.domain.repository.TimerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class TimerRepositoryImpl @Inject constructor() : TimerRepository {

    private val _timerState = MutableStateFlow(
        TimerEntity(
            remainingMillis = TimerType.WORK.durationMills,
            isRunning = false,
            type = TimerType.WORK
        )
    )

    private var timerJob: Job? = null

    private val repositoryScope = CoroutineScope(
        Dispatchers.Default + SupervisorJob()
    )


    override fun getTimerFlow(): Flow<TimerEntity> = _timerState.asStateFlow()

    override suspend fun startTimer() {
        if (_timerState.value.isRunning) return
        _timerState.update { it.copy(isRunning = true) }

        timerJob = repositoryScope.launch {
            while (_timerState.value.remainingMillis > 0) {
                delay(1000L)
                _timerState.update {
                    it.copy(remainingMillis = it.remainingMillis - 1000L)
                }
            }
            pauseTimer()
        }
    }

    override suspend fun resetTimer() {
        pauseTimer()
        val currentType = _timerState.value.type
        _timerState.update {
            it.copy(
                remainingMillis = currentType.durationMills
            )
        }
    }

    override suspend fun pauseTimer() {
        timerJob?.cancel()
        _timerState.update {
            it.copy(isRunning = false)
        }
    }

    override suspend fun updateTimer(type: TimerType) {
        pauseTimer()
        _timerState.update {
            it.copy(
                type = type,
                remainingMillis = type.durationMills
            )
        }
    }

}