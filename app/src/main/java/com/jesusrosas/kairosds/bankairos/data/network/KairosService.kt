package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities.Login
import com.jesusrosas.kairosds.bankairos.core.RetrofitHelper
import com.jesusrosas.kairosds.bankairos.data.model.Success
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.account.*
import com.jesusrosas.kairosds.bankairos.ui.baseform.register.Register
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KairosService {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val retrofitIntercepted = RetrofitHelper.getRetrofitIntercepted()

    suspend fun getToken(login: Login): Token{
        return withContext(Dispatchers.IO){

        val response = retrofit.create(KairosApiClient::class.java).getToken(login)
        response.body() ?: Token("error")
        }
    }

    suspend fun createUser(register: Register): Success {
        return withContext(Dispatchers.IO){

            val response = retrofit.create(KairosApiClient::class.java).createUser(register)
            response.body() ?: Success("error")
        }
    }

    suspend fun getCards(): CardResponse{
        return withContext(Dispatchers.IO){

            val response = retrofitIntercepted.create(KairosApiClient::class.java).getCards()
            response.body() ?: CardResponse(emptyList())
        }
    }

    suspend fun getNewCard(newCard: NewCardItem): Success{
        return withContext(Dispatchers.IO){

            val response = retrofitIntercepted.create(KairosApiClient::class.java).getNewCard(newCard)
            response.body() ?: Success("error")
        }
    }

    suspend fun getCardTypes(): CardTypesResponse {
        return withContext(Dispatchers.IO){

            val response = retrofitIntercepted.create(KairosApiClient::class.java).getCardTypes()
            response.body() ?: CardTypesResponse(CardTypesBody("", emptyList()))
        }
    }

}