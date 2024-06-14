package com.example.message.service

import com.example.message.dto.NewUser
import com.example.message.dto.UserKeyPair

interface IUserService{
    fun registerUser(newUser: NewUser): UserKeyPair
}