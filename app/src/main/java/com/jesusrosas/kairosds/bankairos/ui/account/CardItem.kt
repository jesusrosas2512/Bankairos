package com.jesusrosas.kairosds.bankairos.ui.account


data class CardItem(
    var _id: String,
    var name: String,
    var type: String,
    var userId: String,
    var deposits: Int,
    var withdrawals: Int,
    var balance: Int
)
