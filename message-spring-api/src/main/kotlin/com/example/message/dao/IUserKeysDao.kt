package com.example.message.dao

import com.example.message.dto.User

interface IUserKeysDao{
    fun saveKeyPair(userId: Int, publicKey: String, privateKey: String): User
}