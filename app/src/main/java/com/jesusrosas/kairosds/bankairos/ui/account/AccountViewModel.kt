package com.jesusrosas.kairosds.bankairos.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {
    private val _title = MutableLiveData("Mis cuentas")
    val title: LiveData<String> get() = _title
}