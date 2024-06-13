package com.example.message.dao

import com.example.message.domain.UserKeyPair
import org.springframework.jdbc.core.JdbcTemplate
import java.security.KeyPairGenerator

class UserKeysDao(private val template: JdbcTemplate): IUserKeysDao{
    override fun generateKeyPair(userId: Int): UserKeyPair {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val publicKey = keyPair.public.encoded.joinToString("")
        val privateKey = keyPair.private.encoded.joinToString("")

        val insertKeysQuery = """
            INSERT INTO user_keys (user_id, public_key, private_key) VALUES (?, ?, ?) RETURNING user_id, public_key, private_key
        """.trimIndent()

        return template.queryForObject(
            insertKeysQuery,
            {
                rs, _ -> UserKeyPair(
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