package com.kstuca.shumovych.master.library.controller

import com.kstuca.shumovych.master.library.exception.BookNotFoundException
import com.kstuca.shumovych.master.library.exception.ErrorResponse
import com.kstuca.shumovych.master.library.exception.OrderNotFoundException
import com.kstuca.shumovych.master.library.exception.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorController {

    @ExceptionHandler(BookNotFoundException::class)
    fun handleBookNotFoundException(exception: BookNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(exception.error, exception.errorDescription), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(OrderNotFoundException::class)
    fun handleOrderNotFoundException(exception: OrderNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(exception.error, exception.errorDescription), HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(exception: UserNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse(exception.error, exception.errorDescription), HttpStatus.NOT_FOUND)
    }
}