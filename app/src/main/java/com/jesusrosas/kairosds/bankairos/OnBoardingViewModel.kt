package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido"
    }
    val text: LiveData<String> = _text

    private val _location = MutableLiveData("")
    val location: LiveData<String> get() = _location

    fun setLocation(address: String){
        _location.value = address
    }

}