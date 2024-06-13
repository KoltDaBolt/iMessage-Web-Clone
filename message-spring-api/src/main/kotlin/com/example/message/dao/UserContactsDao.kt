package com.example.message.dao

import com.example.message.exception.DuplicateContactsException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate

class UserContactsDao(private val template: JdbcTemplate): IUserContactsDao{
    override fun createContact(sourceId: Int, targetId: Int, firstname: String, lastname: String): Int{
        val createContactQuery = """
            INSERT INTO user_contacts (source_id, target_id, target_firstname, target_lastname) VALUES (?, ?, ?, ?)
        """.trimIndent()

        return try{
            template.update(createContactQuery, sourceId, targetId, firstname, lastname)
        }catch(e: DataIntegrityViolationException){
            throw DuplicateContactsException("Contact combination already exists!")
        }
    }
}