package com.example.message.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler{
    @ExceptionHandler(DuplicateUsernameException::class)
    fun handleDuplicateUsernameException(ex: DuplicateUsernameException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)

    @ExceptionHandler(DuplicateContactsException::class)
    fun handleDuplicateContactsException(ex: DuplicateContactsException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)

    @ExceptionHandler(GenericException::class)
    fun handleGenericException(ex: GenericException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.message)
}