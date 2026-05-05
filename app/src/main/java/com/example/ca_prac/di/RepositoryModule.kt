package com.example.ca_prac.di

import com.example.ca_prac.data.repository.TimerRepositoryImpl
import com.example.ca_prac.domain.repository.TimerRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent



@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    abstract fun bindTimerRepository(
        timerRepositoryImpl: TimerRepositoryImpl
    ): TimerRepository
}