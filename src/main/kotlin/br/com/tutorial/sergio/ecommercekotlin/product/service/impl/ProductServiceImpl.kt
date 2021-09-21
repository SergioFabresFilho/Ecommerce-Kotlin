package br.com.tutorial.sergio.ecommercekotlin.product.service.impl

import br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.NotFoundException
import br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.exceptionMessage.ExceptionMessage
import br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper.ProductMapper
import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.domain.response.ProductGetResponse
import br.com.tutorial.sergio.ecommercekotlin.product.repository.ProductRepository
import br.com.tutorial.sergio.ecommercekotlin.product.service.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        private val productMapper: ProductMapper,
        private val productRepository: ProductRepository,
) : ProductService {

    override fun create(productCreateRequest: ProductCreateRequest): Long {
        val product = productMapper.toProduct(productCreateRequest)
        val persistedProduct = productRepository.save(product)

        if (persistedProduct.id == null) {
            throw RuntimeException("An error ocurred while saving a product.")
        }

        return persistedProduct.id
    }

    override fun findById(id: Long): ProductGetResponse {
        val product = productRepository.findById(id).orElseThrow { NotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND) }

        return productMapper.toProductGetResponse(product)
    }
}