package com.example.message.domain

data class UserKeyPair(
    val userId: Int,
    val publicKey: String,
    val privateKey: String
)
