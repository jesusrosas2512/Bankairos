package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class OnBoardingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "On Boarding"
    }
    val text: LiveData<String> = _text

}