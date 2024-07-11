package com.example.message.unit.dao

import com.example.message.dao.IUserDao
import com.example.message.dao.UserDao
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.jdbc.core.JdbcTemplate

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserDaoUnitTest{
    lateinit var template: JdbcTemplate
    lateinit var subject: IUserDao

    val insertIntoUsersQuery = "INSERT INTO users (username, password_hash) VALUES (?, ?)"

    @BeforeAll
    fun setup(){
        val dataSource = DataSourceBuilder.create()
            .url("jdbc:mariadb://localhost:3306/messagedb")
            .username("user-message-api")
            .password("user-message-api-password")
            .build()

        template = JdbcTemplate(dataSource)
        subject = UserDao(template)
    }

    @BeforeEach
    fun cleanupDB(){
        val deleteAllUsers = "DELETE FROM users"
        template.update(deleteAllUsers)
    }

    @Test
    fun `createUser - correctly inserts user details`(){
        // given
        val givenUsername = "user:"
        val givenPassword = "password:"

        // when
        val actualUsername = subject.createUser(givenUsername, givenPassword)

        // then
        assertThat(actualUsername, equalTo(givenUsername))
    }
}