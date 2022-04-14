package com.jesusrosas.kairosds.bankairos.data

import com.jesusrosas.kairosds.bankairos.data.model.Success
import com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.data.model.TokenProvider
import com.jesusrosas.kairosds.bankairos.data.network.KairosService
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import com.jesusrosas.kairosds.bankairos.ui.account.CardTypesResponse
import com.jesusrosas.kairosds.bankairos.ui.account.NewCardItem
import com.jesusrosas.kairosds.bankairos.ui.baseform.register.Register

class Repository {

    private val api = KairosService()

    suspend fun getToken(login: Login): Token{
        val response = api.getToken(login)
        TokenProvider.token = response
        return response
    }

    suspend fun createUser(register: Register): Success {
        return api.createUser(register)
    }

    suspend fun getCards(): CardResponse {
        return api.getCards()
    }

    suspend fun getNewCard(newCard: NewCardItem): Success {
        return api.getNewCard(newCard)
    }

    suspend fun getCardTypes(): CardTypesResponse {
        return api.getCardTypes()
    }

}