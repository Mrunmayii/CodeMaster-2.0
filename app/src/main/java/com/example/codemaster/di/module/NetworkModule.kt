package com.example.codemaster.di.module

import com.example.codemaster.data.source.remote.retrofit.CFCCApi
import com.example.codemaster.data.source.remote.retrofit.CodeforcesApi
import com.example.codemaster.data.source.remote.retrofit.ContestApi
import com.example.codemaster.data.source.remote.retrofit.LeetcodeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideCCCF(@Named("CFCC") retrofit: Retrofit) : CFCCApi {
        return retrofit.create(CFCCApi::class.java)
    }
    @Provides
    fun provideLeetcode(@Named("Leetcode") retrofit: Retrofit) : LeetcodeApi {
        return retrofit.create(LeetcodeApi::class.java)
    }

    @Provides
    fun provideContestDetails(@Named("ContestDetails") retrofit: Retrofit) : ContestApi {
        return retrofit.create(ContestApi::class.java)
    }

    @Provides
    fun provideUserRatingChange(@Named("UserRatingChange") retrofit : Retrofit) : CodeforcesApi {
        return retrofit.create(CodeforcesApi::class.java)
    }


}