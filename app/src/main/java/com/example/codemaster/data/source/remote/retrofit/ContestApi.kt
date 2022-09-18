package com.example.codemaster.data.source.remote.retrofit

import com.example.codemaster.data.model.Contest
import retrofit2.Response
import retrofit2.http.GET

interface ContestApi {

    // Contest Details
    @GET("api/v1/all")
    suspend fun getContestDetails() : Response<Contest>
}