package com.example.message.service

import com.example.message.dto.UserSignupFormData
import com.example.message.dto.User
import com.example.message.dto.UserLoginCredentials

interface IUserService{
    fun getLoginCredentials(username: String): UserLoginCredentials
    fun registerUser(newUser: UserSignupFormData): User
    fun login(username: String): User
}