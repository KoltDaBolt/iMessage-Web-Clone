package com.example.message.dao

import com.example.message.dto.User
import com.example.message.dto.UserKeys
import com.example.message.dto.UserSignupFormData
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.queryForObject

class UserKeysDao(private val template: JdbcTemplate): IUserKeysDao{
    override fun saveKeyPair(newUser: UserSignupFormData, userId: String, publicKey: String, privateKey: String): User {
        val insertKeysQuery = """
            INSERT INTO user_keys (user_id, public_key, private_key) VALUES (?, ?, ?) RETURNING user_id, public_key, private_key
        """.trimIndent()

        return template.queryForObject(
            insertKeysQuery,
            {
                rs, _ -> User(
                    newUser.username,
                    newUser.firstname,
                    newUser.lastname,
                    rs.getString("public_key"),
                    rs.getString("private_key")
                )
            },
            userId,
            publicKey,
            privateKey
        )!!
    }

    override fun getKeysFromId(userId: Int): UserKeys{
        val query = """
            SELECT public_key, private_key FROM user_keys WHERE user_id = ?
        """.trimIndent()

        return template.queryForObject(
            query,
            {
                rs, _ -> UserKeys(
                    rs.getString("public_key"),
                    rs.getString("private_key")
                )
            },
            userId
        )!!
    }
}