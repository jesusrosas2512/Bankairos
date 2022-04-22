package com.jesusrosas.kairosds.bankairos.ui.baseform.utils

import com.jesusrosas.kairosds.bankairos.R

enum class ErrorMessage(val msg: Int) {
    EmptyField(R.string.error_empty_field),
    PasswordNotMatch(R.string.password_does_not_mach),
    InvalidEmail(R.string.email_error),
    PasswordLoginError(R.string.password_login_error),
    EmailLoginError(R.string.email_login_error)
}