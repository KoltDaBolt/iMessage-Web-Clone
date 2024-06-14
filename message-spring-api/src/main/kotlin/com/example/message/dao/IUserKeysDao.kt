package com.example.message.dao

import com.example.message.dto.UserKeyPair

interface IUserKeysDao{
    fun saveKeyPair(userId: Int, publicKey: String, privateKey: String): UserKeyPair
}