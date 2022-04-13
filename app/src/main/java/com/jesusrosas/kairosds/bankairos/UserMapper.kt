package com.jesusrosas.kairosds.bankairos

import com.jesusrosas.kairosds.bankairos.data.model.UserProvider
import io.github.nefilim.kjwt.JWT

class UserMapper {

    fun tokenToUserMapper(token: String){
        JWT.decode(token).tap {
            UserProvider.user.apply {
                it.claimValue("id").tap { name -> this.id = name }
                it.claimValue("email").tap { name -> this.email = name }
                it.claimValue("firstname").tap { name -> this.firstname = name }
                it.claimValue("lastname").tap { name -> this.lastname = name }
            }
        }
    }
}