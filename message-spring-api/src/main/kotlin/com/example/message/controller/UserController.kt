package com.example.message.controller

import com.example.message.dao.IUserContactsDao
import com.example.message.dao.IUserDao
import com.example.message.dao.IUserKeysDao
import com.example.message.domain.NewUser
import com.example.message.domain.UserKeyPair
import com.example.message.exception.DuplicateContactsException
import com.example.message.exception.DuplicateUsernameException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/api/user", produces = ["application/json"])
class UserController(
    private val userDao: IUserDao,
    private val userContactsDao: IUserContactsDao,
    private val userKeysDao: IUserKeysDao
){
    @PostMapping("/register")
    fun registerUser(@RequestBody newUser: NewUser): ResponseEntity<Any>{
        return try{
            val newUserId = userDao.addUser(newUser.username, newUser.password)
            userContactsDao.createContact(newUserId, newUserId, newUser.firstname, newUser.lastname)
            val userKeys: UserKeyPair = userKeysDao.generateKeyPair(newUserId)
            ResponseEntity.status(HttpStatus.OK).body(userKeys)
        }catch(e: DuplicateUsernameException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
        }catch(e: DuplicateContactsException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.message)
        }
    }
}