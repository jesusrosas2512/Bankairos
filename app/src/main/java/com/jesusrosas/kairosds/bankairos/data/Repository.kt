package com.jesusrosas.kairosds.bankairos.data

import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.data.model.TokenProvider
import com.jesusrosas.kairosds.bankairos.data.network.KairosService

class Repository {

    private val api = KairosService()

    suspend fun getToken(login: Login): Token{
        val response = api.getToken(login)
        TokenProvider.token = response
        return response
    }

}