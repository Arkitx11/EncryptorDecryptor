package com.example.encryptordecryptor.ui.homeScreen

data class HomeScreenState(
    val optionToggler: Boolean = false,
    val userInputMessage: String = "",
    val mechanism: Int = 0
)

