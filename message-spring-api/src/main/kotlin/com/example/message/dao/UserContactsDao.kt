package com.example.message.dao

import com.example.message.dto.FirstNameLastName
import com.example.message.exception.DuplicateContactsException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class UserContactsDao(private val template: JdbcTemplate): IUserContactsDao{
    override fun createContact(sourceId: String, targetId: String, firstname: String, lastname: String): Int{
        val createContactQuery = """
            INSERT INTO user_contacts (source_id, target_id, target_firstname, target_lastname) VALUES (?, ?, ?, ?)
        """.trimIndent()

        return try{
            template.update(createContactQuery, sourceId, targetId, firstname, lastname)
        }catch(e: DataIntegrityViolationException){
            throw DuplicateContactsException("Contact combination already exists!")
        }
    }

    override fun getFirstNameLastNameFromId(userId: Int): FirstNameLastName{
        val query = """
            SELECT target_firstname, target_lastname FROM user_contacts WHERE source_id = ? AND target_id = ?
        """.trimIndent()

        return template.queryForObject(
            query,
            {
                rs, _ -> FirstNameLastName(
                    rs.getString("target_firstname"),
                    rs.getString("target_lastname")
                )
            },
            userId,
            userId
        )!!
    }
}