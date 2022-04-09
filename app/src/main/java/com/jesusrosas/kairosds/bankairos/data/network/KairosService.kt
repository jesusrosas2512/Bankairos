package com.jesusrosas.kairosds.bankairos.data.network

import com.jesusrosas.kairosds.bankairos.LocationReq
import com.jesusrosas.kairosds.bankairos.LocationResponse
import com.jesusrosas.kairosds.bankairos.PlusCode
import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.core.RetrofitHelper
import com.jesusrosas.kairosds.bankairos.data.model.Token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KairosService {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val retroGeo = RetrofitHelper.getRetroGeo()

    suspend fun getToken(login: Login): Token{
        return withContext(Dispatchers.IO){

        val response = retrofit.create(KairosApiClient::class.java).getToken(login)
        response.body() ?: Token("error")
        }
    }

    suspend fun getLocation(location: LocationReq): LocationResponse{
        return withContext(Dispatchers.IO){

            val response = retrofit.create(KairosApiClient::class.java).getLocation(location.latLng, location.locType, location.resType, location.key)
            response.body() ?: LocationResponse(PlusCode("Ubicación desconocida", "Error"))
        }
    }
}