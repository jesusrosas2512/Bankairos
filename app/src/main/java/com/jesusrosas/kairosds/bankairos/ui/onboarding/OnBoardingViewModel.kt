package com.jesusrosas.kairosds.bankairos.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bienvenido"
    }
    val text: LiveData<String> = _text

    private val _goToFormFragment: MutableLiveData<String?> = MutableLiveData()
    val goToFormFragment: LiveData<String?> get() = _goToFormFragment

    private val _location = MutableLiveData("")
    val location: LiveData<String> get() = _location

    fun setLocation(address: String){
        _location.value = address
    }

    fun goToFormClicked(formType: String){
        _goToFormFragment.value = formType
    }

}