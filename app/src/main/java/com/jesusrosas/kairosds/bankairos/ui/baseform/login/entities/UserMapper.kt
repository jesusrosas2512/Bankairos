package com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities

import com.jesusrosas.kairosds.bankairos.data.model.UserProvider
import io.github.nefilim.kjwt.JWT

class UserMapper {

    fun tokenToUserMapper(token: String){
        JWT.decode(token).tap {
            UserProvider.user.apply {
                it.claimValue("id").tap { id -> this.id = id }
                it.claimValue("email").tap { email -> this.email = email }
                it.claimValue("firstname").tap { firstname -> this.firstname = firstname }
                it.claimValue("lastname").tap { lastname -> this.lastname = lastname }
                it.issuedAt().tap { name -> this.iat = name.toString() }
                it.expiresAt().tap { name -> this.expat = name.toString() }
            }
        }
    }
}