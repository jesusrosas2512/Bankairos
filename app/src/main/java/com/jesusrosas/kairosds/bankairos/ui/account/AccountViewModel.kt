package com.jesusrosas.kairosds.bankairos.ui.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrosas.kairosds.bankairos.domain.GetCardsUseCase
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel() {

    private val getCardsUseCase = GetCardsUseCase()

    private val _title = MutableLiveData("Mis cuentas")
    val title: LiveData<String> get() = _title

    private val _frame = MutableLiveData("Accounts")
    val frame: LiveData<String> get() = _frame

    private val _isMsgVisible = MutableLiveData(false)
    val isMsgVisible: LiveData<Boolean> get() = _isMsgVisible

    fun init() {
        viewModelScope.launch {
            val result = getCardsUseCase()
            for (element in result.cards){
                if (element.name == "Error") _isMsgVisible.value = true
                else Log.i("Debug", "${element.name} - ${element.type}")
            }
        }

    }
}