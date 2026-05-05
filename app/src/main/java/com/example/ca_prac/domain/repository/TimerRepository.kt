package com.example.ca_prac.domain.repository
import com.example.ca_prac.domain.model.TimerEntity
import com.example.ca_prac.domain.model.TimerType
import kotlinx.coroutines.flow.Flow

interface TimerRepository {
    fun getTimerFlow(): Flow<TimerEntity>
    suspend fun startTimer()
    suspend fun pauseTimer()
    suspend fun updateTimer(type: TimerType)
    suspend fun resetTimer()
}