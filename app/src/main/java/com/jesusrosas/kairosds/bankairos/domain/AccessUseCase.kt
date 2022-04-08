package com.jesusrosas.kairosds.bankairos.domain

import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.data.model.Token

class AccessUseCase {

    private val repository = Repository()

    suspend operator fun invoke(login: Login): Token{
        return repository.getToken(login)
    }
}