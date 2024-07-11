package com.example.message.dao

import com.example.message.dto.UserLoginCredentials

interface IUserDao{
    fun getLoginCredentials(username: String): UserLoginCredentials
    fun createUser(username: String, password: String): String
    fun getUserIdFromUsername(username: String): Int
}