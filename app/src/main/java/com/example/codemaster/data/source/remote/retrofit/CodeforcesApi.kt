package com.example.codemaster.data.source.remote.retrofit

import com.example.codemaster.data.model.codeforces_offical.CodeforcesProblemset
import com.example.codemaster.data.model.codeforces_offical.UserInfo
import com.example.codemaster.data.model.codeforces_offical.UserInfoResult
import com.example.codemaster.data.model.codeforces_offical.UserRatingChange
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CodeforcesApi {

    @GET("user.rating?")
    suspend fun getUserRatingChange(
        @Query("handle") handle : String
    ) : Response<UserRatingChange>

    @GET("problemset.problems?")
    suspend fun getUserProblemset(
        @Query("tags") tags : String
    ) : Response<CodeforcesProblemset>

    @GET("user.info?")
    suspend fun getUserInfo(
        @Query("handle") handle : String
    ) : Response<UserInfo>
}