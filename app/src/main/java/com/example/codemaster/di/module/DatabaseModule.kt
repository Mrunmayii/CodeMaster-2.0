package com.example.codemaster.di.module

import android.app.Application
import androidx.room.Room
import com.example.codemaster.data.source.local.dao.UsernameDao
import com.example.codemaster.data.source.local.database.UsernameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesUsernameDatabse(app:Application) : UsernameDatabase {
        return Room.databaseBuilder(
            app,
            UsernameDatabase::class.java,
            "username_db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideUsernameDao(database: UsernameDatabase): UsernameDao {
        return database.usernameDao
    }

}