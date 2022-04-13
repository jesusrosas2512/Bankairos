package com.jesusrosas.kairosds.bankairos.ui.baseform.viewmodel

import androidx.lifecycle.*
import com.jesusrosas.kairosds.bankairos.ui.baseform.ErrorMessage
import com.jesusrosas.kairosds.bankairos.ui.baseform.Event
import com.jesusrosas.kairosds.bankairos.ui.baseform.ValidationRules
import com.jesusrosas.kairosds.bankairos.domain.AccessUseCase
import com.jesusrosas.kairosds.bankairos.ui.baseform.login.entities.Login
import com.jesusrosas.kairosds.bankairos.ui.baseform.register.Register
import kotlinx.coroutines.launch

class BaseFormViewModel : ViewModel() {
    val accessUseCase = AccessUseCase()
    private val validationRules = ValidationRules()

    private val _frame = MutableLiveData("login")
    val frame: LiveData<String> get() = _frame

    private val _tokenAccess = MutableLiveData<String>()
    val tokenAccess: LiveData<String> get() = _tokenAccess

    private val _isFormValid = MutableLiveData(false)
    val isFormValid: LiveData<Boolean> get() = _isFormValid

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    private val _emailError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _passwordError = MutableLiveData<Event<ErrorMessage?>?>()
    val passwordError: LiveData<Event<ErrorMessage?>?> get() = _passwordError
    val emailError: LiveData<Event<ErrorMessage?>?> get() = _emailError

    val emailReg = MutableLiveData<String>()
    val passwordReg = MutableLiveData<String>()
    val nameReg = MutableLiveData<String>()
    val lastNameReg = MutableLiveData<String>()
    val confirmPassReg = MutableLiveData<String>()
    private val _emailRegError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _lastNameRegError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _nameRegError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _passwordRegError = MutableLiveData<Event<ErrorMessage?>?>()
    private val _confirmPassRegError = MutableLiveData<Event<ErrorMessage?>?>()
    val passwordRegError: LiveData<Event<ErrorMessage?>?> get() = _passwordRegError
    val lastNameRegError: LiveData<Event<ErrorMessage?>?> get() = _lastNameRegError
    val nameRegError: LiveData<Event<ErrorMessage?>?> get() = _nameRegError
    val emailRegError: LiveData<Event<ErrorMessage?>?> get() = _emailRegError
    val confirmPassRegError: LiveData<Event<ErrorMessage?>?> get() = _confirmPassRegError

    fun changeForm(form: String){
        _isFormValid.value = false
        _frame.value = form
    }

    fun btnAccessClicked(){
        if (_frame.value == "Ingresar") {
            login()
        } else {
            register()
        }
    }

    private fun login(){
        viewModelScope.launch {
            val loginRequest = Login(email.value.toString(), password.value.toString())
            val result = accessUseCase(loginRequest)
            _tokenAccess.value = if (result.token != "error") "Acceso exitoso"
            else result.token
        }

    }

    private fun register(){
        viewModelScope.launch {
            val registerRequest = Register(emailReg.value.toString(),
                nameReg.value.toString(),
                lastNameReg.value.toString(),
                passwordReg.value.toString(),)
            val result = accessUseCase(registerRequest)
            _tokenAccess.value = result.success
        }
    }

    fun emailChanged(editTextValue: String){
        editTextValue.let {
            email.postValue(it.trim())
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
        }
        validateForm()
    }

    fun passwordChanged(editTextValue: String){
        editTextValue.let {
            password.postValue(it.trim())
            _passwordError.value = if (it.isNotBlank()) {
                Event(null)
            } else {
                Event(ErrorMessage.EmptyField)
            }

            validateForm()
        }
    }

    fun emailRegChanged(editTextValue: String){
        editTextValue.let {
            emailReg.postValue(it.trim())
            if (it.isNotBlank()) {
                _emailRegError.value = Event(null)
            } else {
                _emailRegError.value = Event(ErrorMessage.EmptyField)
                validateForm()
                return
            }

            _emailRegError.value = if (validationRules.isEmailValid(it)) {
                Event(null)
            } else {
                Event(ErrorMessage.InvalidEmail)
            }
        }
        validateForm()
    }

    fun passwordRegChanged(editTextValue: String){
        editTextValue.let {
            passwordReg.postValue(it.trim())
            _passwordRegError.value = if (it.isNotBlank()) {
                Event(null)
            } else {
                Event(ErrorMessage.EmptyField)
            }

            if (!confirmPassReg.value.isNullOrEmpty()){
                _confirmPassRegError.value = if (it != confirmPassReg.value) {
                    Event(ErrorMessage.PasswordNotMatch)
                } else {
                    Event(null)
                }
            }

            validateForm()

        }

    }

    fun confirmPassRegChanged(editTextValue: String?){
        editTextValue?.let {
            _confirmPassRegError.value = if (it != passwordReg.value) {
                Event(ErrorMessage.PasswordNotMatch)
            } else {
                Event(null)
            }

            validateForm()
        }
    }

    fun nameRegChanged(editTextValue: String){
        editTextValue.let {
            if (it.isNotBlank()) {
                _nameRegError.value = Event(null)
            } else {
                _nameRegError.value = Event(ErrorMessage.EmptyField)
                validateForm()
                return
            }

            validateForm()
        }
    }

    fun lastNameRegChanged(editTextValue: String){
        editTextValue.let {
            if (it.isNotBlank()) {
                _lastNameRegError.value = Event(null)
            } else {
                _lastNameRegError.value = Event(ErrorMessage.EmptyField)
                validateForm()
                return
            }
            validateForm()
        }
    }

    private fun validateForm() {
        _isFormValid.value =
        if (_frame.value == "Ingresar") {

            (email.value != null && emailError.value?.peekContent() == null) &&
            (password.value != null && passwordError.value?.peekContent() == null)

        } else {
            (emailReg.value != null && emailError.value?.peekContent() == null) &&
            (passwordReg.value != null && passwordRegError.value?.peekContent() == null) &&
            (nameReg.value != null && nameRegError.value?.peekContent() == null) &&
            (lastNameReg.value != null && lastNameRegError.value?.peekContent() == null) &&
            (confirmPassReg.value != null && confirmPassRegError.value?.peekContent() == null)
        }
    }
}