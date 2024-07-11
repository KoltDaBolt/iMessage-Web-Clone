package com.example.message.dao

import com.example.message.dto.UserLoginCredentials
import com.example.message.exception.DuplicateUsernameException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.jdbc.core.JdbcTemplate

class UserDao(val template: JdbcTemplate): IUserDao{
    override fun getLoginCredentials(username: String): UserLoginCredentials{
        val getUserCredsQuery = """
            SELECT username, password_hash FROM users WHERE username = ?
        """.trimIndent()

        return try{
            template.queryForObject(
                getUserCredsQuery,
                {
                    rs, _ -> UserLoginCredentials(
                        rs.getString("username"),
                        rs.getString("password_hash")
                    )
                },
                username
            )
        }catch(e: Exception){
            UserLoginCredentials("", "")
        }!!
    }

    override fun createUser(username: String, password: String): String{
        val createUserQuery = """
            INSERT INTO users (username, password_hash) VALUES (?, ?) RETURNING username
        """.trimIndent()

        return try{
            template.queryForObject(
                createUserQuery,
                { rs, _ -> rs.getString("username") },
                username,
                password
            )
        }catch(e: DataIntegrityViolationException){
            throw DuplicateUsernameException("Username $username is unavailable. Please pick another.")
        }!!
    }

    override fun getUserIdFromUsername(username: String): Int{
        val query = """
            SELECT id FROM users WHERE username = ?
        """.trimIndent()

        return template.queryForObject(
            query,
            { rs, _ -> rs.getInt("id") },
            username
        )!!
    }
}