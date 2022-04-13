package com.jesusrosas.kairosds.bankairos

import io.github.nefilim.kjwt.JWSES256Algorithm
import io.github.nefilim.kjwt.JWT
import io.github.nefilim.kjwt.JWT.Companion.hs256

fun main(){
    println("Testing kJWT")

    val jwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjYyM2JiMzk1MTVhYWE1MDAxNmY3MzFjOSIsImVtYWlsIjoianJiLjI1MTJAZ21haWwuY29tIiwiZmlyc3RuYW1lIjoiSmVzdXMiLCJsYXN0bmFtZSI6IlJvc2FzIiwiaWF0IjoxNjQ4NjEyNjg2LCJleHAiOjE2NDk5MDg2ODZ9.MwCSjpRn01h5aKaaq9-LJ7-CyEwjRQKagP2A2eeP_2I"
    val jwtInstance = JWTSpec()
/*    JWT.decode(jwtToken).tap {
        println("Decoding")
    }*/


}

class JWTSpec {
    init {
        JWT.decode("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c").tap {
            println("the issuer is: ${it.issuer()}")
            println("the subject is: ${it.subject()}")
        }
    }
}