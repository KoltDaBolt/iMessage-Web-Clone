package com.example.message.dao

interface IUserDao{
    fun addUser(username: String, password: String): Int
}