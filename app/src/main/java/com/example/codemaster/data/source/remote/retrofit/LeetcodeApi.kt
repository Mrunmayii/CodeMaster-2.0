package com.example.codemaster.data.source.remote.retrofit

import com.example.codemaster.data.model.Leetcode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LeetcodeApi {

    // leetcode
    @GET("api/leetcode/{userName}")
    suspend fun getLeetcode(
        @Path("userName") userName : String
    ) : Response<Leetcode>

}