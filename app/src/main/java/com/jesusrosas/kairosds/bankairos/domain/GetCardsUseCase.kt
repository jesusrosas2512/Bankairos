package com.jesusrosas.kairosds.bankairos.domain

import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.data.model.Success
import com.jesusrosas.kairosds.bankairos.ui.account.CardResponse
import com.jesusrosas.kairosds.bankairos.ui.account.CardTypesResponse
import com.jesusrosas.kairosds.bankairos.ui.account.NewCardItem

class GetCardsUseCase {

    private val repository = Repository()

    suspend operator fun invoke(): CardResponse {
        return repository.getCards()
    }

    suspend operator fun invoke(newCard: NewCardItem): Success {
        return repository.getNewCard(newCard)
    }

}