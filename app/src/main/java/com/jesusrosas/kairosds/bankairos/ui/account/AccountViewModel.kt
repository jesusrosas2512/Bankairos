package com.jesusrosas.kairosds.bankairos.ui.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jesusrosas.kairosds.bankairos.data.model.CardNamesProvider
import com.jesusrosas.kairosds.bankairos.data.model.LocationProvider
import com.jesusrosas.kairosds.bankairos.data.model.UserProvider
import com.jesusrosas.kairosds.bankairos.domain.GetCardTypesUseCase
import com.jesusrosas.kairosds.bankairos.domain.GetCardsUseCase
import kotlinx.coroutines.launch

class AccountViewModel : ViewModel(), OnCardOptionListener {

    private val getCardsUseCase = GetCardsUseCase()
    private val getCardTypesUseCase = GetCardTypesUseCase()

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _frame = MutableLiveData("Accounts")
    val frame: LiveData<String> get() = _frame

    private val _name = MutableLiveData(UserProvider.getFullName())
    val name: LiveData<String> get() = _name

    private val _email = MutableLiveData(UserProvider.user.email)
    val email: LiveData<String> get() = _email

    private val _location = MutableLiveData(LocationProvider.location)
    val location: LiveData<String> get() = _location

    private val _isBtnEnabled = MutableLiveData(false)
    val isBtnEnabled: LiveData<Boolean> get() = _isBtnEnabled

    private val _isMsgVisible = MutableLiveData(false)
    val isMsgVisible: LiveData<Boolean> get() = _isMsgVisible

    private val _cardOptions = MutableLiveData<List<String>>()
    val cardOptions: LiveData<List<String>> get() = _cardOptions

    private val _cardTypes = MutableLiveData<List<String>>()
    val cardTypes: LiveData<List<String>> get() = _cardTypes

    private val _selectedCard = MutableLiveData<String>()
    private val _selectedCardType = MutableLiveData<String>()

    private val _newCardSuccess = MutableLiveData<String>()
    val newCardSuccess: LiveData<String> get() = _newCardSuccess

    private val _cardList = MutableLiveData<List<CardItem>>()
    val cardList: LiveData<List<CardItem>> get() = _cardList
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun changeView(frameView: String) {

        if (frameView == "Accounts"){
            _title.value = "Mis cuentas"
            initMyAccounts()
        } else {
            _title.value = "Solicitar tarjeta"
            initSelectCard()
        }

        _frame.value = frameView
    }

    private fun initMyAccounts() {
        _isLoading.value = true

        viewModelScope.launch {
            val result = getCardsUseCase()
            val cards = result.cards
            if (cards.isNotEmpty()) {
                for (element in cards) {
                    if (element.name == "") _isMsgVisible.value = true
                    else {
                        _isMsgVisible.value = false
                        _cardList.value = cards
                    }

                }
            } else _isMsgVisible.value = true

            _isLoading.value = false
        }
    }

    private fun initSelectCard() {
        _cardOptions.value = CardNamesProvider.getCardsList()

        val listOfTypes = mutableListOf<String>()

        viewModelScope.launch {
            val result = getCardTypesUseCase()
            val cardTypes = result.types.type_cards
            if (cardTypes.isNotEmpty()) {
                for (element in cardTypes) {
                    if (element.name != ""){
                        listOfTypes.add(element.type)
                        _cardTypes.value = listOfTypes
                    }
                }
            }
        }
    }

    override fun onCardOptionClicked(position: Int) {
        _selectedCard.value =
            _cardOptions.value?.get(position).orEmpty()

        validateForm()
    }

    override fun onCardTypeClicked(position: Int) {
        _selectedCardType.value =
            _cardTypes.value?.get(position).orEmpty()

        validateForm()
    }

    fun btnRequestClicked(){
        viewModelScope.launch {
            val newCard = NewCardItem(
                UserProvider.user.id,
                _selectedCardType.value.toString(),
                _selectedCard.value.toString()
            )
            val result = getCardsUseCase(newCard)
            if (result.success != "error"){
                _newCardSuccess.value = result.success
                _isBtnEnabled.value = false
            }

        }

    }

    private fun validateForm() {
        _isBtnEnabled.value = (!_selectedCard.value.isNullOrEmpty() && !_selectedCardType.value.isNullOrEmpty())
    }
}