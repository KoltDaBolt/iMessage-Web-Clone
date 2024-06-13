package com.example.message.dao

interface IUserContactsDao{
    fun createContact(sourceId: Int, targetId: Int, firstname: String, lastname: String): Int
}