package com.jesusrosas.kairosds.bankairos.ui.login.viewmodel

import androidx.lifecycle.*
import com.jesusrosas.kairosds.bankairos.ErrorMessage
import com.jesusrosas.kairosds.bankairos.Event
import com.jesusrosas.kairosds.bankairos.ValidationRules
import com.jesusrosas.kairosds.bankairos.domain.AccessUseCase
import com.jesusrosas.kairosds.bankairos.ui.login.entities.Login
import kotlinx.coroutines.launch

class BaseFormViewModel : ViewModel() {
    val loginUseCase = AccessUseCase()
    private val validationRules = ValidationRules()

    private val _email = MutableLiveData("")
    private val _password = MutableLiveData("")
    val email: LiveData<String> get() = _email
    val password: LiveData<String> get() = _password

    private val _emailError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _passwordError = MutableLiveData<Event<ErrorMessage?>?>()
    val passwordError: LiveData<Event<ErrorMessage?>?> get() = _passwordError
    val emailError: LiveData<Event<ErrorMessage?>?> get() = _emailError

    private val _frame = MutableLiveData("login")
    val frame: LiveData<String> get() = _frame

    private val _tokenAccess = MutableLiveData<String>()
    val tokenAccess: LiveData<String> get() = _tokenAccess

    private val _isFormValid = MutableLiveData<Boolean>()
    val isFormValid: LiveData<Boolean> get() = _isFormValid

    fun changeForm(form: String){
        _frame.postValue(form)
    }

    fun login(){
        viewModelScope.launch {
            val loginRequest = Login(_email.value.toString(), _password.value.toString())
            val result = loginUseCase(loginRequest)
            _tokenAccess.value = result.token
        }
    }

    fun emailChanged(editTextValue: String){
        editTextValue.let {
            _email.postValue(it.trim())
            if (it.isNotBlank()) {
                _emailError.value = Event(null)
            } else {
                _emailError.value = Event(ErrorMessage.EmptyField)
                validateForm()
                return
            }

            _emailError.value = if (validationRules.isEmailValid(it)) {
                Event(null)
            } else {
                Event(ErrorMessage.InvalidEmail)
            }
            validateForm()
        }
    }

    fun passwordChanged(editTextValue: String){
        editTextValue.let {
            _password.postValue(it.trim())
            _passwordError.value = if (it.isNotBlank()) {
                Event(null)
            } else {
                Event(ErrorMessage.EmptyField)
            }

            validateForm()
        }
    }

    private fun validateForm() {
        _isFormValid.value =
            (_email.value != null && emailError.value?.peekContent() == null) &&
                    (_password.value != null && passwordError.value?.peekContent() == null)
    }
}