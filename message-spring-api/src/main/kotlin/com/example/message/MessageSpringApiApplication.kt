package com.example.message

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessageSpringApiApplication

fun main(args: Array<String>) {
	runApplication<MessageSpringApiApplication>(*args)
}