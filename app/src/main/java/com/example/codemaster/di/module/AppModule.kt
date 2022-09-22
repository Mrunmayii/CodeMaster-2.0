package com.example.codemaster.di.module

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    @Named("Leetcode")
    fun providesLeetcodeApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://competitive-coding-api.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("CFCC")
    fun providesCfCcApi(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://contest-details.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Provides
    @Singleton
    @Named("ContestDetails")
    fun providesContestDetails(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://kontests.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("UserRatingChange")
    fun providesUserRatingChange(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://codeforces.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}