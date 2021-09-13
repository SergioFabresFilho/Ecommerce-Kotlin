package br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper.impl

import br.com.tutorial.sergio.ecommercekotlin.product.domain.entity.Product
import br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper.ProductMapper
import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse
import org.springframework.stereotype.Component

@Component
class ProductMapperImpl : ProductMapper {
    override fun toProduct(productCreateRequest: ProductCreateRequest): Product {
        return Product(null, productCreateRequest.name, productCreateRequest.value)
    }

    override fun toProductGetResponse(product: Product): ProductGetResponse {
        return ProductGetResponse(product.name, product.value)
    }
}