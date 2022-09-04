package com.example.codemaster.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.codemaster.data.source.local.dao.UsernameDao
import com.example.codemaster.data.source.local.enitity.Username


@Database(
    entities = [Username::class],
    version = 1
)
abstract class UsernameDatabase : RoomDatabase(){


    // instance of dao classs for accessing functions of dao
    abstract val usernameDao : UsernameDao
}