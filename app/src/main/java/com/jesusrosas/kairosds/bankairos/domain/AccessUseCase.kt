package com.jesusrosas.kairosds.bankairos.domain

import com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities.Login
import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.data.model.Success
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.baseform.register.Register

class AccessUseCase {

    private val repository = Repository()

    suspend operator fun invoke(login: Login): Token{
        return repository.getToken(login)
    }

    suspend operator fun invoke(register: Register): Success {
        return repository.createUser(register)
    }
}