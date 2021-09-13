package br.com.tutorial.sergio.ecommercekotlin.product.service.impl

import br.com.tutorial.sergio.ecommercekotlin.product.domain.entity.Product
import br.com.tutorial.sergio.ecommercekotlin.product.domain.exception.ProductNotFountException
import br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper.ProductMapper
import br.com.tutorial.sergio.ecommercekotlin.product.domain.mother.ProductMother
import br.com.tutorial.sergio.ecommercekotlin.product.repository.ProductRepository
import org.assertj.core.api.BDDAssertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import java.util.*

class ProductServiceImplUnitTest {

    private val productMapper = Mockito.mock(ProductMapper::class.java)
    private val productRepository = Mockito.mock(ProductRepository::class.java)
    private val productService = ProductServiceImpl(productMapper, productRepository)

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(productMapper, productRepository)
    }

    @Test
    fun `Given valid parameters When create Then return persisted id`() {
        val productCreateRequest = ProductMother.createProductCreateRequest()
        val mapperResponse = ProductMother.createProduct()
        val repositoryResponse = ProductMother.createProduct()

        given(productMapper.toProduct(productCreateRequest)).willReturn(mapperResponse)
        given(productRepository.save(mapperResponse)).willReturn(repositoryResponse)

        val id = productService.create(productCreateRequest)

        then(id).isEqualTo(repositoryResponse.id)

        verify(productMapper, times(1)).toProduct(productCreateRequest)
        verify(productRepository, times(1)).save(mapperResponse)
    }

    @Test
    fun `Given repository returns null id When create Then should throw exception`() {
        val productCreateRequest = ProductMother.createProductCreateRequest()
        val mapperResponse = ProductMother.createProduct()
        val repositoryResponse = Product(null, "asdf", 12.34)

        given(productMapper.toProduct(productCreateRequest)).willReturn(mapperResponse)
        given(productRepository.save(mapperResponse)).willReturn(repositoryResponse)

        thenExceptionOfType(RuntimeException::class.java).isThrownBy {
            productService.create(productCreateRequest)
        }

        verify(productMapper, times(1)).toProduct(productCreateRequest)
        verify(productRepository, times(1)).save(mapperResponse)
    }

    @Test
    fun `Given valid id When findById Then return ProductGetResponse`() {
        val id = 1L
        val product = ProductMother.createProduct()
        val productGetResponse = ProductMother.createProductGetResponse()

        given(productRepository.findById(id)).willReturn(Optional.of(product))
        given(productMapper.toProductGetResponse(product)).willReturn(productGetResponse)

        val serviceResponse = productService.findById(id)

        then(serviceResponse).isEqualTo(productGetResponse)

        verify(productRepository, times(1)).findById(id)
        verify(productMapper, times(1)).toProductGetResponse(product)
    }

    @Test
    fun `Given invalid id When findById Then throw ProductNotFoundException`() {
        val id = 1L

        given(productRepository.findById(id)).willReturn(Optional.empty())

        thenExceptionOfType(ProductNotFountException::class.java).isThrownBy {
            productService.findById(id)
        }

        verify(productRepository, times(1)).findById(id)
    }
}