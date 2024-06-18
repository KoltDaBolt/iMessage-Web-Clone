package com.example.message.dto

data class User(
    val username: String,
    val firstname: String,
    val lastname: String,
    val publicKey: String?,
    val privateKey: String?
)
