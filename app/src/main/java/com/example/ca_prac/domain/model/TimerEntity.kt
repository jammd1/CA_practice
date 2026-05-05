package com.example.ca_prac.domain.model

data class TimerEntity(
    val remainingMillis: Long,
    val isRunning: Boolean,
    val type: TimerType = TimerType.WORK
)

enum class TimerType(val durationMills: Long) {
    WORK(25 * 60 * 1000L),
    BREAK(5 * 60 * 1000L),
    LONG_BREAK(15 * 60 * 1000L)
}