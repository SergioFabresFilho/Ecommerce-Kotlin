package br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper

import br.com.tutorial.sergio.ecommercekotlin.product.domain.entity.Product
import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse

interface ProductMapper {
    fun toProduct(productCreateRequest: ProductCreateRequest): Product
    fun toProductGetResponse(product: Product): ProductGetResponse
}