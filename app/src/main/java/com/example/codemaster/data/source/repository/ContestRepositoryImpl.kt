package com.example.codemaster.data.source.repository

import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.Leetcode
import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset
import com.example.codemaster.data.model.codeforces_offical.UserInfo
import com.example.codemaster.data.model.codeforces_offical.UserRatingChange
import com.example.codemaster.data.source.local.dao.UsernameDao
import com.example.codemaster.data.source.local.enitity.CCUsername
import com.example.codemaster.data.source.local.enitity.CFUsername
import com.example.codemaster.data.source.local.enitity.LCUsername
import com.example.codemaster.data.source.local.enitity.Username
import com.example.codemaster.data.source.remote.retrofit.CFCCApi
import com.example.codemaster.data.source.remote.retrofit.CodeforcesApi
import com.example.codemaster.data.source.remote.retrofit.ContestApi
import com.example.codemaster.data.source.remote.retrofit.LeetcodeApi
import com.example.codemaster.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import com.example.codemaster.utils.Result
import javax.inject.Inject


class ContestRepositoryImpl @Inject constructor(

    private var leetcodeApi: LeetcodeApi,
    private var cfccApi: CFCCApi,
    private var contestApi: ContestApi,
    private var codeforcesApi: CodeforcesApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val usernameDao: UsernameDao

) : ContestRepository {

    // Remote implementation
    override suspend fun getCodechef(userName: String): Result<Codechef?> =
        withContext(ioDispatcher) {
            try {
                val result = cfccApi.getCodechef(userName)
                if(result.isSuccessful) Result.Success(data = result.body())
                else Result.Error(message = "error")
            } catch(e: Exception) {
                e.printStackTrace()
                Result.Error(message = "exception error")
            }
        }
    override suspend fun getCodeforces(userName: String): Result<Codeforces?> =
        withContext(ioDispatcher){
            return@withContext try{
                val resource = cfccApi.getCodeforces(userName).body()
                if(resource == null) Result.Error(message = "invalid username")
                else Result.Success(resource)
            } catch (exception: Exception) {
                Result.Error(message = "Error fetching codeforces data")
            }
        }
    override suspend fun getLeetCode(userName: String): Result<Leetcode?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val resource = leetcodeApi.getLeetcode(userName).body()
                Result.Success(resource)
            } catch (exception: Exception) {
                Result.Error(message = "Error fetching leetcode data")
            }
        }
    override suspend fun getContestDetails(): Result<Contest?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val resource = contestApi.getContestDetails().body()
                Result.Success(resource)
            } catch (exception: Exception) {
                Result.Error(message = "Error fetching contest data")
            }
        }

    override suspend fun getUserRatingChange(handle:String): Result<UserRatingChange?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val resource = codeforcesApi.getUserRatingChange(handle).body()
                if (resource != null) {
                    Result.Success(resource)
                } else {
                    Result.Error("error fetching user rating change")
                }
            } catch(exception : Exception){
                Result.Error(message = "Error fetching contest data")
            }
        }

    override suspend fun getProblemset(tags: String): Result<CodeforcesProblemset?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val resource = codeforcesApi.getUserProblemset(tags).body()
                Result.Success(resource)
            } catch (exception : Exception) {
                Result.Error(message = "error fetching problemset")
            }
        }

    override suspend fun getUserInfo(handle: String): Result<UserInfo?> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = codeforcesApi.getUserInfo(handle).body()
                Result.Success(response)
            } catch (exception : Exception) {
                Result.Error(message = "Error fetching user information")
            }
        }

    override suspend fun storeCodechefUsername(userName: CCUsername) {
        withContext(ioDispatcher){
            usernameDao.storeCodechefUsername(userName)
        }
    }

    override suspend fun storeCodeforcesUsername(userName: CFUsername) {
        withContext(ioDispatcher){
            usernameDao.storeCodeforcesUsername(userName)
        }
    }

    override suspend fun storeLeetcodeUsername(userName: LCUsername) {
        withContext(ioDispatcher){
            usernameDao.storeLeetcodeUsername(userName)
        }
    }

    override suspend fun storeUsername(username: Username) {
        withContext(ioDispatcher){
            usernameDao.storeUsername(username)
        }
    }

    override suspend fun getCCUsername(id: Int): CCUsername? {
        return usernameDao.getCCUsername(id)
    }

    override suspend fun getCFUsername(id: Int): CFUsername? {
        return usernameDao.getCFUsername(id)
    }

    override suspend fun getLCUsername(id: Int): LCUsername? {
        return usernameDao.getLCUsername(id)
    }


    // local implementation
//    override suspend fun storeCodechefUsername(userName: Username) =
//        withContext(ioDispatcher){
//            usernameDao.storeCodechefUsername(userName)
//        }
//    override suspend fun storeCodeforcesUsername(userName: Username) =
//        withContext(ioDispatcher){
//            usernameDao.storeCodeforcesUsername(userName)
//        }
//    override suspend fun storeLeetcodeUsername(userName: Username) =
//        withContext(ioDispatcher){
//            usernameDao.storeLeetcodeUsername(userName)
//        }

    override suspend fun getUsername(id: Int): Username? {
        return usernameDao.getUsername(id)
    }

}