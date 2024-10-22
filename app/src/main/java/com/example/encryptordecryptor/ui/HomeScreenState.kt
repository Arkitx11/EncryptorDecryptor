package com.example.encryptordecryptor.ui

data class HomeScreenState(
    val optionToggler: Boolean = false,
    val userInputMessage: String = "",
    val mechanism: Int = 0,
    val outputMessage: String = ""
)


enum class Mechanism {
    ROT13, AES, RSA
}