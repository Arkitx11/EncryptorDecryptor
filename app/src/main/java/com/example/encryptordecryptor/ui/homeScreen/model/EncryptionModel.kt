package com.example.encryptordecryptor.ui.homeScreen.model

import androidx.lifecycle.ViewModel
import com.example.encryptordecryptor.ui.homeScreen.HomeScreenState
import com.example.encryptordecryptor.utils.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class EncryptionModel : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    val homeScreenState = _homeScreenState.asStateFlow()

    private val _encryptedMessage: String = rot13(homeScreenState.value.userInputMessage)
    val encryptedMessage
        get() = _encryptedMessage
}