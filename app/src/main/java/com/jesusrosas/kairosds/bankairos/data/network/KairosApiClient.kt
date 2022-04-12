package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import retrofit2.Response
import retrofit2.http.*

interface KairosApiClient {

    @POST("auth/user/authenticate/")
    suspend fun getToken(
        @Body login: Login
    ): Response<Token>

    @Headers("X-access-token: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyM2JiMzk1MTVhYWE1MDAxNmY3MzFjOSIsImVtYWlsIjoianJiLjI1MTJAZ21haWwuY29tIiwiZmlyc3RuYW1lIjoiSmVzdXMiLCJsYXN0bmFtZSI6IlJvc2FzIiwiaWF0IjoxNjQ4NjEyNjg2LCJleHAiOjE2NDk5MDg2ODZ9.MwCSjpRn01h5aKaaq9-LJ7-CyEwjRQKagP2A2eeP_2I")
    @GET("accounts")
    suspend fun getCards(
    ): Response<CardResponse>

}