package com.example.message.service

import com.example.message.dao.IUserContactsDao
import com.example.message.dao.IUserDao
import com.example.message.dao.IUserKeysDao
import com.example.message.dto.NewUser
import com.example.message.dto.UserKeyPair
import com.example.message.exception.DuplicateContactsException
import com.example.message.exception.DuplicateUsernameException
import com.example.message.exception.GenericException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.KeyPairGenerator

@Service
class UserService(
    private val userDao: IUserDao,
    private val userContactsDao: IUserContactsDao,
    private val userKeysDao: IUserKeysDao
): IUserService{
    @Transactional
    override fun registerUser(newUser: NewUser): UserKeyPair{
        return try{
            val newUserId = userDao.addUser(newUser.username, newUser.password)

            userContactsDao.createContact(newUserId, newUserId, newUser.firstname, newUser.lastname)
            generateKeyPair(newUserId)
        }catch(e: DuplicateUsernameException){
            throw DuplicateUsernameException("Username ${newUser.username} is unavailable. Please pick another.")
        }catch(e: DuplicateContactsException){
            throw DuplicateContactsException("Users already have contacts with eachother!")
        }catch(e: Exception){
            throw GenericException("An unexpected error has occurred!")
        }
    }

    private fun generateKeyPair(userId: Int): UserKeyPair{
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val publicKey = keyPair.public.encoded.joinToString("")
        val privateKey = keyPair.private.encoded.joinToString("")

        return userKeysDao.saveKeyPair(userId, publicKey, privateKey)
    }
}