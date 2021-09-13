package br.com.tutorial.sergio.ecommercekotlin.product.service

import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse

interface ProductService {
    fun create(productCreateRequest: ProductCreateRequest): Long
    fun findById(id: Long): ProductGetResponse
}