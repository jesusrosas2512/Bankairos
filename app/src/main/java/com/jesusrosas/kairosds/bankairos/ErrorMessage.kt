package com.jesusrosas.kairosds.bankairos

enum class ErrorMessage(val msg: Int) {
    EmptyField(R.string.error_empty_field),
    PasswordNotMatch(R.string.password_does_not_mach),
    InvalidPassword(R.string.invalid_password),
    InvalidEmail(R.string.email_error),
    PasswordLoginError(R.string.password_login_error),
    EmailLoginError(R.string.email_login_error)
}