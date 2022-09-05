package com.example.codemaster.di.module

import android.content.Context
import com.example.codemaster.data.source.remote.retrofit.CfCcAPi
import com.example.codemaster.data.source.remote.retrofit.ContestApi
import com.example.codemaster.data.source.remote.retrofit.LeetcodeApi
import com.example.codemaster.data.source.repository.ContestRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideCCCF(@Named("CFCC") retrofit: Retrofit) : CfCcAPi {
        return retrofit.create(CfCcAPi::class.java)
    }
    @Provides
    fun provideLeetcode(@Named("Leetcode") retrofit: Retrofit) : LeetcodeApi {
        return retrofit.create(LeetcodeApi::class.java)
    }

    @Provides
    fun provideContestDetails(@Named("ContestDetails") retrofit: Retrofit) : ContestApi {
        return retrofit.create(ContestApi::class.java)
    }


}