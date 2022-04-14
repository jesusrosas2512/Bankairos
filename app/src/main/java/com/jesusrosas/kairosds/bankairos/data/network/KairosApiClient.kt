package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.data.model.Success
import com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import com.jesusrosas.kairosds.bankairos.ui.account.CardTypesResponse
import com.jesusrosas.kairosds.bankairos.ui.account.NewCardItem
import com.jesusrosas.kairosds.bankairos.ui.baseform.register.Register
import retrofit2.Response
import retrofit2.http.*

interface KairosApiClient {

    @POST("auth/user/authenticate")
    suspend fun getToken(
        @Body login: Login
    ): Response<Token>

    @POST("auth/user/create")
    suspend fun createUser(
        @Body register: Register
    ): Response<Success>

    @POST("accounts")
    suspend fun getNewCard(
        @Body newCard: NewCardItem
    ): Response<Success>

    @GET("accounts")
    suspend fun getCards(
    ): Response<CardResponse>

    @GET("catalogs/cards")
    suspend fun getCardTypes(
    ): Response<CardTypesResponse>

}