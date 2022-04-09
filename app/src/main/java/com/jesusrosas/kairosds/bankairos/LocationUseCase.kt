package com.jesusrosas.kairosds.bankairos

import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login

class LocationUseCase {

    private val repository = Repository()

    suspend operator fun invoke(location: LocationReq): LocationResponse {
        return repository.getLocation(location)
    }

}