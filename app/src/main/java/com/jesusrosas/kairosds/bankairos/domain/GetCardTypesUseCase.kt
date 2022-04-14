package com.jesusrosas.kairosds.bankairos.domain

import com.jesusrosas.kairosds.bankairos.data.Repository
import com.jesusrosas.kairosds.bankairos.ui.account.CardTypesResponse

class GetCardTypesUseCase {

    private val repository = Repository()

    suspend operator fun invoke(): CardTypesResponse {
        return repository.getCardTypes()
    }
}