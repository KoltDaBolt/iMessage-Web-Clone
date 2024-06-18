package com.example.message.dao

import com.example.message.dto.User
import org.springframework.jdbc.core.JdbcTemplate

class UserKeysDao(private val template: JdbcTemplate): IUserKeysDao{
    override fun saveKeyPair(userId: Int, publicKey: String, privateKey: String): User {
        val insertKeysQuery = """
            INSERT INTO user_keys (user_id, public_key, private_key) VALUES (?, ?, ?) RETURNING user_id, public_key, private_key
        """.trimIndent()

        return template.queryForObject(
            insertKeysQuery,
            {
                rs, _ -> User(
                    rs.getInt("user_id"),
                    rs.getString("public_key"),
                    rs.getString("private_key")
                )
            },
            userId,
            publicKey,
            privateKey
        )!!
    }
}