package com.example.message.dao

import com.example.message.dto.FirstNameLastName

interface IUserContactsDao{
    fun createContact(sourceId: String, targetId: String, firstname: String, lastname: String): Int
    fun getFirstNameLastNameFromId(userId: Int): FirstNameLastName
}