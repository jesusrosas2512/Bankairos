package com.jesusrosas.kairosds.bankairos

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface APIService {
    /*@Headers("Content-type: application/json")
    @GET
    suspend fun login(@Url url:String) : Response<LoginResponse>*/

    @POST
    suspend fun login(@Body emailPassword: BasicUserParams ) : Call<*>
}