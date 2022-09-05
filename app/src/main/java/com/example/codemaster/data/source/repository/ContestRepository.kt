package com.example.codemaster.data.source.repository

import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.Leetcode
import com.example.codemaster.data.source.datastore.User
import com.example.codemaster.data.source.local.enitity.Username
import kotlinx.coroutines.flow.Flow

interface ContestRepository {

    // remote database
    suspend fun getCodechef(userName : String) : Result<Codechef?>
    suspend fun getCodeforces(userName : String) : Result<Codeforces?>
    suspend fun getLeetCode(userName: String) : Result<Leetcode?>
    suspend fun getContestDetails() : Result<List<Contest>?>

    // room database
    suspend fun storeCodechefUsername(userName : Username)
    suspend fun storeCodeforcesUsername(userName : Username)
    suspend fun storeLeetcodeUsername(userName: Username)

    // datastore
    suspend fun saveUser(user: User)
    suspend fun getUser() : Flow<User>
}