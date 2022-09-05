package com.example.codemaster.data.source.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.Leetcode
import com.example.codemaster.data.source.datastore.User
import com.example.codemaster.data.source.local.dao.UsernameDao
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.remote.retrofit.CfCcAPi
import com.example.codemaster.data.source.remote.retrofit.ContestApi
import com.example.codemaster.data.source.remote.retrofit.LeetcodeApi
import com.example.codemaster.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject



class ContestRepositoryImpl @Inject constructor(

    private var leetcodeApi : LeetcodeApi,
    private var cfccApi : CfCcAPi,
    private var contestApi : ContestApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val usernameDao: UsernameDao,
    private val context: Context

) : ContestRepository {

    // Remote implementation
    override suspend fun getCodechef(userName: String): Result<Codechef?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = cfccApi.getCodechef(userName)
                if (result.isSuccessful) {
                    val codechefData = result.body()
                    Result.success(codechefData)
                } else {
                    Log.d("kalp", result.body().toString())
                    Result.success(null)
                }
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    override suspend fun getCodeforces(userName: String): Result<Codeforces?> =
        withContext(ioDispatcher){
            return@withContext try{
                val result = cfccApi.getCodeforces(userName)
                if (result.isSuccessful) {
                    val codeforcesData = result.body()
                    Result.success(codeforcesData)
                }
                else {
                    val notCodeforcesData = result.body()
                    Result.success(notCodeforcesData)
                }
            }
            catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    override suspend fun getLeetCode(userName: String): Result<Leetcode?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = leetcodeApi.getLeetcode(userName)
                if (result.isSuccessful) {
                    val LeetcodeData = result.body()
                    Result.success(LeetcodeData)
                } else {
                    Result.success(null)
                }
            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    override suspend fun getContestDetails(): Result<List<Contest>?> =
        withContext(ioDispatcher){
            return@withContext try {
                val result = contestApi.getContestDetails()
                if (result.isSuccessful) {
                    val LeetcodeData = result.body()
                    Result.success(LeetcodeData)
                } else {
                    Result.success(null)
                }
            }
            catch (exception: Exception) {
                Result.failure(exception)
            }
        }

    // local implementation
    override suspend fun storeCodechefUsername(userName: Username) =
        withContext(ioDispatcher){
            usernameDao.storeCodechefUsername(userName)
        }
    override suspend fun storeCodeforcesUsername(userName: Username) =
        withContext(ioDispatcher){
            usernameDao.storeCodeforcesUsername(userName)
        }
    override suspend fun storeLeetcodeUsername(userName: Username) =
        withContext(ioDispatcher){
            usernameDao.storeLeetcodeUsername(userName)
        }


    // data store implementation
    val datastore_name = "USERNAME"
    val Context.datastore : DataStore<Preferences> by preferencesDataStore(name = datastore_name)
    companion object {
        val CODECHEF_USER = stringPreferencesKey("CODECHEF_USER")
        val CODEFORCES_USER = stringPreferencesKey("CODEFORCES_USER")
        val LEETCODE_USER = stringPreferencesKey("LEETCODE_USER")
    }
    override suspend fun saveUser(user : User) {
        context.datastore.edit {
            it[CODECHEF_USER] = user.codechef_user
            it[CODEFORCES_USER] = user.codeforces_user
            it[LEETCODE_USER] = user.leetcode_user
        }
    }
    override suspend fun getUser() = context.datastore.data.map {
        User(
            codechef_user = it[CODECHEF_USER]!!,
            codeforces_user = it[CODEFORCES_USER]!!,
            leetcode_user = it[LEETCODE_USER]!!
        )
    }
}