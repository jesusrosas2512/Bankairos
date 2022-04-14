package com.jesusrosas.kairosds.bankairos.ui.account

import com.google.gson.annotations.SerializedName

data class CardTypesResponse(
    @SerializedName("response") var types: CardTypesBody
)
