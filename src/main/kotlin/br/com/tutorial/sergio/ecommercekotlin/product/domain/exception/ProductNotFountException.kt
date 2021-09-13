package br.com.tutorial.sergio.ecommercekotlin.product.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class ProductNotFountException() : RuntimeException("Produto não encontrado.")