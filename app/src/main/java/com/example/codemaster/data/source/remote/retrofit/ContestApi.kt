package com.example.codemaster.data.source.remote.retrofit

import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import com.example.codemaster.data.model.Contest
import com.example.codemaster.data.model.Leetcode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ContestApi {

    // Contest Details
    @GET("api/v1/all")
    suspend fun getContestDetails(
    ) : Response<List<Contest>>
}