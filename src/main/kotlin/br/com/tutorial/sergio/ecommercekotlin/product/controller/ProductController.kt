package br.com.tutorial.sergio.ecommercekotlin.product.controller

import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse
import br.com.tutorial.sergio.ecommercekotlin.product.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/products")
class ProductController(
        private val productService: ProductService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody productCreateRequest: ProductCreateRequest): Long {
        val id = productService.create(productCreateRequest)
        return id
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable("id") id: Long): ProductGetResponse {
        return productService.findById(id)
    }
}