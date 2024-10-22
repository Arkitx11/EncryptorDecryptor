package com.example.encryptordecryptor.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeScreenModel : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    private var _optionToggler: Boolean = false
    private var _outputMessage: String = ""
    private lateinit var _userInputMessage: String
    private var encryptionSelector: Mechanism = Mechanism.ROT13


    val optionToggler
        get() = _optionToggler

    val userInputMessage
        get() = _userInputMessage

    val outputMessage
        get() = _outputMessage

    val homeScreenState = _homeScreenState.asStateFlow()

    fun onPressingOptionToggler(value: Boolean) {
        _optionToggler = value
    }

    fun onSubmit(input: String) {
        _userInputMessage = input
    }

    fun encryptionSelector(index: Int) {
        encryptionSelector = Mechanism.entries[index]
    }

}