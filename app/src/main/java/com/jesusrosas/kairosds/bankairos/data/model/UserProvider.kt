package com.jesusrosas.kairosds.bankairos.data.model

import kotlin.math.exp

class UserProvider {

    companion object{
        var user = User("", "", "", "", "", "")

        fun getFullName(): String{
            return "${user.firstname} ${user.lastname}"
        }

        fun closeSession() {
            user.apply {
                id = ""
                email = ""
                firstname = ""
                lastname = ""
                iat = ""
                expat = ""
            }
        }
    }

}