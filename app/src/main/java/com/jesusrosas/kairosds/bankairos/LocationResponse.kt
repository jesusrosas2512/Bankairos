package com.jesusrosas.kairosds.bankairos

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("plus_code") val plusCode: PlusCode
)

data class PlusCode(
    @SerializedName("compound_code") val location: String,
    @SerializedName("global_code") val code: String
)
