package com.example.message.dao

import com.example.message.domain.UserKeyPair

interface IUserKeysDao{
    fun generateKeyPair(userId: Int): UserKeyPair
}