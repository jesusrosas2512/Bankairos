package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class OnBoardingViewModel : ViewModel() {
    val locationUseCase = LocationUseCase()
    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido"
    }
    val text: LiveData<String> = _text

    private val _location = MutableLiveData("")
    val location: LiveData<String> get() = _location

    fun setLocation(lat: String, lng: String){
        viewModelScope.launch {
            val locationRequest = LocationReq("${lat},${lng}", "ROOFTOP", "country", "AIzaSyBvjb6eeMhTPofo16NDeN70jePwLKcTeeA")
            val result = locationUseCase(locationRequest)
            _location.value = with(result){
                this.plusCode.location
            }
        }
    }

}