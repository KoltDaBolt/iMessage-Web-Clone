package com.example.message.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate

@Configuration
class ApplicationConfig{
    @Bean
    fun template(): JdbcTemplate{
        return JdbcTemplate(MariaDBConfig().dataSource())
    }
}