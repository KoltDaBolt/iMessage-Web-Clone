package com.example.message.config

import org.mariadb.jdbc.MariaDbDataSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class MariaDBConfig{

    // DON'T HARD CODE YOUR VARIABLES LIKE THIS
    // ALSO CHOOSE A BETTER PASSWORD LOL
    @Bean
    fun dataSource(): DataSource{
        val dataSource = MariaDbDataSource()
        dataSource.url = "jdbc:mariadb://localhost:3306/messagedb?useSSL=false&allowPublicKeyRetrieval=true"
        dataSource.user = "user-message-api"
        dataSource.setPassword("user-message-api-password")

        return dataSource
    }
}