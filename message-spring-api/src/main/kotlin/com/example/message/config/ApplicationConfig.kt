package com.example.message.config

import com.example.message.dao.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class ApplicationConfig{
    @Bean
    fun template(): JdbcTemplate{
        return JdbcTemplate(MariaDBConfig().dataSource())
    }

    @Bean
    fun userDao(template: JdbcTemplate): IUserDao = UserDao(template)

    @Bean
    fun userContactsDao(template: JdbcTemplate): IUserContactsDao = UserContactsDao(template)

    @Bean
    fun userKeysDao(template: JdbcTemplate): IUserKeysDao = UserKeysDao(template)
}