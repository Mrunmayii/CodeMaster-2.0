package com.example.codemaster.data.source.remote.retrofit

import com.example.codemaster.data.model.Codechef
import com.example.codemaster.data.model.Codeforces
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import com.example.codemaster.utils.Result

interface CfCcAPi {

    @GET("codechef/{userName}")
    suspend fun getCodechef(
        @Path("userName") userName : String
    ) : Response<Codechef>

    @GET("codeforces/{userName}")
    suspend fun getCodeforces(
        @Path("userName") userName : String
    ) : Response<Codeforces>

}