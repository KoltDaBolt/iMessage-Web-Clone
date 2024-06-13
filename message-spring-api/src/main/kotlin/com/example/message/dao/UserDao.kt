package com.example.message.dao

import com.example.message.exception.DuplicateUsernameException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate

class UserDao(val template: JdbcTemplate): IUserDao{
    override fun addUser(username: String, password: String): Int{
        val createUserQuery = """
            INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING id
        """.trimIndent()

        return try{
            template.queryForObject(
                createUserQuery,
                { rs, _ -> rs.getInt("id") },
                username,
                password
            )
        }catch(e: DataIntegrityViolationException){
            throw DuplicateUsernameException("Username $username is unavailable. Please pick another.")
        }!!
    }
}