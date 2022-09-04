package com.example.codemaster.data.source.repository

import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.Leetcode
import com.example.codemaster.data.source.local.dao.UsernameDao
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.remote.retrofit.CfCcAPi
import com.example.codemaster.data.source.remote.retrofit.ContestApi
import com.example.codemaster.data.source.remote.retrofit.LeetcodeApi
import com.example.codemaster.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ContestRepositoryImpl @Inject constructor(

    private var leetcodeApi : LeetcodeApi,
    private var cfccApi : CfCcAPi,
    private var contestApi : ContestApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val usernameDao: UsernameDao

) : ContestRepository {

    override suspend fun getCodechef(userName: String): Result<Codechef?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = cfccApi.getCodechef(userName)
                if (result.isSuccessful) {
                    val CodechefData = result.body()
                    Result.success(CodechefData)
                } else {
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
                    val CodeforcesData = result.body()
                    Result.success(CodeforcesData)
                }
                else {
                    Result.success(null)
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

}