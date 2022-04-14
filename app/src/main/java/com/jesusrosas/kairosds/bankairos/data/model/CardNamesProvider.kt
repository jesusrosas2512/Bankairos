package com.jesusrosas.kairosds.bankairos.data.model

class CardNamesProvider {

    companion object {
        private val cardsList: List<String> = listOf(
            "Tarjeta Oro",
            "Tarjeta Premium",
            "Tarjeta Black"
        )

        fun getCardsList(): List<String>{
            return cardsList
        }
    }

}