package com.example.encryptordecryptor.utils

fun rot13(input: String): String {
    return input.map { char ->
        when (char) {
            in 'a'..'z' -> 'a' + (char - 'a' + 13) % 26
            in 'A'..'Z' -> 'A' + (char - 'A' + 13) % 26
            else -> char
        }
    }.joinToString("")
}