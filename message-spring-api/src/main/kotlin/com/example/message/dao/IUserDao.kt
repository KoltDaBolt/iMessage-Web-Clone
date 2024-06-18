package com.example.message.dao

import com.example.message.dto.UserLoginCredentials

interface IUserDao{
    fun getLoginCredentials(username: String): UserLoginCredentials
    fun addUser(username: String, password: String): Int
}