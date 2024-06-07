package com.example.message.dao

import com.example.message.exception.DuplicateUsernameException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate

class UserDao(private val template: JdbcTemplate): IUserDao{
    override fun addUser(username: String, password: String): Int{
        val createUserQuery = """
            INSERT INTO users (username, password_hash) values (?, ?)
        """.trimIndent()

        return try{
            template.update(createUserQuery, username, password)
        }catch(e: DataIntegrityViolationException){
            throw DuplicateUsernameException("Username $username is unavailable. Please pick another.")
        }
    }
}