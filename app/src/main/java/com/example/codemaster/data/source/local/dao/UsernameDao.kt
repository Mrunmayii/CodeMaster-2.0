package com.example.codemaster.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.codemaster.data.source.local.enitity.Username


@Dao
interface UsernameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCodechefUsername(username : Username)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCodeforcesUsername(username : Username)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeLeetcodeUsername(username : Username)

}