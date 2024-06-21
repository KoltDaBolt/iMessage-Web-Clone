package com.example.message.dao

import com.example.message.dto.User
import com.example.message.dto.UserKeys
import com.example.message.dto.UserSignupFormData

interface IUserKeysDao{
    fun saveKeyPair(newUser: UserSignupFormData, userId: Int, publicKey: String, privateKey: String): User
    fun getKeysFromId(userId: Int): UserKeys
}