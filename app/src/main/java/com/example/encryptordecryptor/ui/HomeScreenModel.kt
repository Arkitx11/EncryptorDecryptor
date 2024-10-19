package com.example.encryptordecryptor.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class HomeScreenModel : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())

}