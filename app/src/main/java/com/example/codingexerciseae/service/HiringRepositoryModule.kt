package com.example.codingexerciseae.service

import com.example.codingexerciseae.repository.HiringRepository
import com.example.codingexerciseae.repository.HiringRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface HiringRepositoryModule {

    @Binds
    fun hiringListRepository(hiringListRepositoryImpl: HiringRepositoryImpl): HiringRepository
}