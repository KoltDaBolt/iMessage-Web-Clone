package com.example.message.controller

import com.example.message.dto.NewUser
import com.example.message.dto.UserKeyPair
import com.example.message.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/user", produces = ["application/json"])
class UserController(
    private val userService: UserService
){
    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: NewUser): ResponseEntity<UserKeyPair>{
        val userKeys: UserKeyPair = userService.registerUser(newUser)
        return ResponseEntity.status(HttpStatus.OK).body(userKeys)
    }
}