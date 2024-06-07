package com.example.message.config

import com.example.message.dao.IUserDao
import com.example.message.dao.UserDao
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
}