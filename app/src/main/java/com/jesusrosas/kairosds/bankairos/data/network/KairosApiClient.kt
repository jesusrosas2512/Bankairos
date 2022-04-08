package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface KairosApiClient {

    @POST("auth/user/authenticate/")
    suspend fun getToken(
        @Body login: Login
    ): Response<Token>
}