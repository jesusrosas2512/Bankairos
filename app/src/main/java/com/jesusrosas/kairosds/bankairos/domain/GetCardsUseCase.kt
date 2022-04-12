package com.jesusrosas.kairosds.bankairos.domain

import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.data.model.Token
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login

class GetCardsUseCase {

    private val repository = Repository()

    suspend operator fun invoke(): CardResponse {
        return repository.getCards()
    }

}