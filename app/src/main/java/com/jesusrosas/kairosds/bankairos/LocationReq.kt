package com.jesusrosas.kairosds.bankairos

import retrofit2.http.Query

data class LocationReq(val latLng: String,
                       val locType: String,
                       val resType: String,
                       val key: String
)
