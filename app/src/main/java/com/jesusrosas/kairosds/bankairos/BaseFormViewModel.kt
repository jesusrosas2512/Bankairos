package com.jesusrosas.kairosds.bankairos

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BaseFormViewModel : ViewModel() {
    private val _frame = MutableLiveData("login")

    val frame: LiveData<String> get() = _frame

    fun changeForm(form: String){
        _frame.postValue(form)

    }


}