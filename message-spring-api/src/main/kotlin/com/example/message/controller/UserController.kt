package com.example.message.controller

import com.example.message.dao.IUserDao
import com.example.message.domain.NewUser
import com.example.message.exception.DuplicateUsernameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/user", produces = ["application/json"])
class UserController(private val userDao: IUserDao){
    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: NewUser): ResponseEntity<Any> {
        // step 1: create a user - params: username, password_hash
        try{
            userDao.addUser(newUser.username, newUser.password)
        }catch(e: DuplicateUsernameException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
        }

        // step 2: create a user_contact with self - params: firstname, lastname
        // step 3: create encryption key pair
        // step 4: upon success, return public and private keys

        return ResponseEntity.status(HttpStatus.OK).body("User created.")
    }
}