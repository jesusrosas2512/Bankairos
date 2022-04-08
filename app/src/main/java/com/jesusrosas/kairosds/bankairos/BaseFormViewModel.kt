package com.jesusrosas.kairosds.bankairos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrosas.kairosds.bankairos.domain.AccessUseCase
import kotlinx.coroutines.launch

class BaseFormViewModel : ViewModel() {
    private val _frame = MutableLiveData("login")
    private val _tokenAccess = MutableLiveData<String>()

    val frame: LiveData<String> get() = _frame
    val tokenAccess: LiveData<String> get() = _tokenAccess

    var loginUseCase = AccessUseCase()

    fun changeForm(form: String){
        _frame.postValue(form)

    }

    fun login(loginData: Login){
        viewModelScope.launch {
            val result = loginUseCase(loginData)
            _tokenAccess.value = result.token
        }
        /*val eMail = "jrb.2512@gmail.com"
        val password = "@kairosD5?"

        val userToken = TokenProvider.getToken().token
        _statusAccess.postValue(userToken)*/

    }
}