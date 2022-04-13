package com.jesusrosas.kairosds.bankairos.data.model

class UserProvider {

    companion object{
        var user = User("", "", "", "")

        fun getFullName(): String{
            return "${user.firstname} ${user.lastname}"
        }
    }

}