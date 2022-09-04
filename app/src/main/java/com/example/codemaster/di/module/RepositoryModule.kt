package com.example.codemaster.di.module

import com.example.codemaster.data.source.repository.ContestRepository
import com.example.codemaster.data.source.repository.ContestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindContestRepository(contestRepositoryImpl: ContestRepositoryImpl) : ContestRepository

}