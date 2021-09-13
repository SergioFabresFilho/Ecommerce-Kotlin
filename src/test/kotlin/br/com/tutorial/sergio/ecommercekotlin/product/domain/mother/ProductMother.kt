package br.com.tutorial.sergio.ecommercekotlin.product.domain.mother

import br.com.tutorial.sergio.ecommercekotlin.product.domain.entity.Product
import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse

class ProductMother {
    companion object {
        fun createProduct(): Product {
            return Product(1L, "product", 1.2)
        }

        fun createProductCreateRequest(): ProductCreateRequest {
            return ProductCreateRequest("product create request", 9.9)
        }

        fun createProductGetResponse(): ProductGetResponse {
            return ProductGetResponse("product get response", 1.23)
        }
    }
}
