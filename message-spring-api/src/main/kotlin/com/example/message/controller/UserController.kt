package com.example.message.controller

import com.example.message.dto.UserSignupFormData
import com.example.message.dto.User
import com.example.message.dto.UserLoginCredentials
import com.example.message.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/user", produces = ["application/json"])
class UserController(
    private val userService: UserService
){
    @GetMapping("/getLoginCredentials/{username}")
    fun getLogicCredentials(@PathVariable username: String): ResponseEntity<UserLoginCredentials>{
        val userLoginCreds: UserLoginCredentials = userService.getLoginCredentials(username)
        return ResponseEntity.status(HttpStatus.OK).body(userLoginCreds)
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: UserSignupFormData): ResponseEntity<User>{
        val userKeys: User = userService.registerUser(newUser)
        return ResponseEntity.status(HttpStatus.OK).body(userKeys)
    }
}