package com.example.message.dao

import com.example.message.dto.FirstNameLastName

interface IUserContactsDao{
    fun createContact(sourceId: Int, targetId: Int, firstname: String, lastname: String): Int
    fun getFirstNameLastNameFromId(userId: Int): FirstNameLastName
}