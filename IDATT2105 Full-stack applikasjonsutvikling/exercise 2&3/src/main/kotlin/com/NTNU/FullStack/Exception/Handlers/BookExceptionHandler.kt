package com.NTNU.FullStack.Exception.Handlers

import com.NTNU.FullStack.Exception.BookNotFoundExecption
import com.NTNU.FullStack.utils.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class BookExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BookNotFoundExecption::class)
    protected fun handleBookNotFound(ex: BookNotFoundExecption, request: WebRequest): ResponseEntity<Any> =
            ResponseEntity(ErrorResponse(ex.message!!) , HttpStatus.NOT_FOUND)
}