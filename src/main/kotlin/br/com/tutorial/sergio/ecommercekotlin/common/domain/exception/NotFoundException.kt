package br.com.tutorial.sergio.ecommercekotlin.common.domain.exception

import br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.exceptionMessage.ExceptionMessage
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(
        exceptionMessage: ExceptionMessage
) : RuntimeException(exceptionMessage.toString())