package com.jesusrosas.kairosds.bankairos.ui.account

import com.google.gson.annotations.SerializedName

data class CardResponse(@SerializedName("response") var cards: List<CardItem>)
