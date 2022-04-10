package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import retrofit2.Response
import retrofit2.http.*

interface KairosApiClient {

    @POST("auth/user/authenticate/")
    suspend fun getToken(
        @Body login: Login
    ): Response<Token>

}