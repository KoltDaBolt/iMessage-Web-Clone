package com.example.message.service

import com.example.message.dao.IUserContactsDao
import com.example.message.dao.IUserDao
import com.example.message.dao.IUserKeysDao
import com.example.message.dto.UserSignupFormData
import com.example.message.dto.User
import com.example.message.dto.UserLoginCredentials
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
    override fun getLoginCredentials(username: String): UserLoginCredentials{
        try{
            return userDao.getLoginCredentials(username)
        }catch(e: Exception){
            throw GenericException("An unexpected error has occurred!")
        }
    }

    @Transactional
    override fun registerUser(newUser: UserSignupFormData): User{
        return try{
            val newUsernameId = userDao.createUser(newUser.username, newUser.password)

            userContactsDao.createContact(newUsernameId, newUsernameId, newUser.firstname, newUser.lastname)
            generateKeyPair(newUser, newUsernameId)
        }catch(e: DuplicateUsernameException){
            throw DuplicateUsernameException("Username ${newUser.username} is unavailable. Please pick another.")
        }catch(e: DuplicateContactsException){
            throw DuplicateContactsException("Users already have contacts with eachother!")
        }catch(e: Exception){
            throw GenericException("An unexpected error has occurred!")
        }
    }

    override fun login(username: String): User{
        val userId = userDao.getUserIdFromUsername(username)
        val userFirstNameLastName = userContactsDao.getFirstNameLastNameFromId(userId)
        val userKeys = userKeysDao.getKeysFromId(userId)

        return User(
            username,
            userFirstNameLastName.firstname,
            userFirstNameLastName.lastname,
            userKeys.publicKey,
            userKeys.privateKey
        )
    }

    private fun generateKeyPair(newUser: UserSignupFormData, userId: String): User{
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(2048)
        val keyPair = keyPairGenerator.generateKeyPair()

        val publicKey = keyPair.public.encoded.joinToString("")
        val privateKey = keyPair.private.encoded.joinToString("")

        return userKeysDao.saveKeyPair(newUser, userId, publicKey, privateKey)
    }
}