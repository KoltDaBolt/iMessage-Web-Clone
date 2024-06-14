package com.example.message.dto

data class UserKeyPair(
    val userId: Int,
    val publicKey: String,
    val privateKey: String
)
