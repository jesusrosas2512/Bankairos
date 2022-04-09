package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido"
    }
    val text: LiveData<String> = _text

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    fun setLocation(location: String){
        _location.value = location
    }

}