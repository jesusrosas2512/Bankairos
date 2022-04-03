package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseFormViewModel : ViewModel() {
    private val _frame = MutableLiveData("register")
    private val _goToRegister = MutableLiveData("register")

    val frame: LiveData<String> get() = _frame

    init {
        initScreen()
    }

    private fun initScreen() {
        _frame.postValue("login")
    }

    fun registerClicked(){

    }

}