package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.core.RetrofitHelper
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.account.CardItem
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KairosService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getToken(login: Login): Token{
        return withContext(Dispatchers.IO){

        val response = retrofit.create(KairosApiClient::class.java).getToken(login)
        response.body() ?: Token("error")
        }
    }

    suspend fun getCards(): CardResponse{
        return withContext(Dispatchers.IO){

            val response = retrofit.create(KairosApiClient::class.java).getCards()
            response.body() ?: CardResponse(listOf(CardItem("623bb8c115aaa50016f731ca","Error","TDC","623bb39515aaa50016f731c9",0,0,0)))
        }
    }

}