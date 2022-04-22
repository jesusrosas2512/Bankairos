package com.jesusrosas.kairosds.bankairos.ui.baseform.utils

class ValidationRules {
    fun isEmailValid(value: String) =
        (
                "^[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(\\.[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+$"
                ).toRegex().matches(value)
}