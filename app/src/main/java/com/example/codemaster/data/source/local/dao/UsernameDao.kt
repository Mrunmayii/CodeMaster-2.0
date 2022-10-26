package com.example.codemaster.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.codemaster.data.source.local.enitity.CCUsername
import com.example.codemaster.data.source.local.enitity.CFUsername
import com.example.codemaster.data.source.local.enitity.LCUsername
import com.example.codemaster.data.source.local.enitity.Username


@Dao
interface UsernameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCodechefUsername(username : CCUsername)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeCodeforcesUsername(username : CFUsername)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeLeetcodeUsername(username : LCUsername)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeUsername(username : Username)

    @Query("SELECT * FROM CCUsername WHERE id = :id")
    suspend fun getCCUsername(id : Int) : CCUsername?

    @Query("SELECT * FROM CFUsername WHERE id = :id")
    suspend fun getCFUsername(id : Int) : CFUsername?

    @Query("SELECT * FROM LCUsername WHERE id = :id")
    suspend fun getLCUsername(id : Int) : LCUsername?

    @Query("SELECT * FROM Username WHERE id = :id")
    suspend fun getUsername(id : Int) : Username?

}