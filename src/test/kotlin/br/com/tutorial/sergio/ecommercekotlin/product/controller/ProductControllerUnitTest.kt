package br.com.tutorial.sergio.ecommercekotlin.product.controller

import br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.NotFoundException
import br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.exceptionMessage.ExceptionMessage
import br.com.tutorial.sergio.ecommercekotlin.product.domain.mother.ProductMother
import br.com.tutorial.sergio.ecommercekotlin.product.domain.request.ProductCreateRequest
import br.com.tutorial.sergio.ecommercekotlin.product.service.ProductService
import br.com.tutorial.sergio.ecommercekotlin.utils.MockitoHelper
import com.google.gson.Gson
import com.nhaarman.mockito_kotlin.argumentCaptor
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.BDDMockito.times
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@SpringBootTest
class ProductControllerUnitTest {

    private val productsUrl = "/v1/products"
    private val productsUrlWithId = "/v1/products/{id}"

    private val productService = Mockito.mock(ProductService::class.java)
    private val productController = ProductController(productService)

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build()
    }

    @AfterEach
    fun tearDown() {
        verifyNoMoreInteractions(productService)
    }

    @Test
    fun `Given valid parameters When create Then return created`() {
        val productCreateRequest = ProductMother.createProductCreateRequest()
        val id = -1L

        given(productService.create(MockitoHelper.anyObject())).willReturn(id)

        val gson = Gson()
        val jsonBody = gson.toJson(productCreateRequest)

        mockMvc.perform(
                MockMvcRequestBuilders.post(productsUrl)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
        ).andExpect(status().isCreated)
                .andExpect(jsonPath("$").value(id))

        argumentCaptor<ProductCreateRequest>().apply {
            verify(productService, times(1)).create(capture())
            then(firstValue).usingRecursiveComparison().isEqualTo(productCreateRequest)
        }
    }

    @Test
    fun `Given valid id When findById Then return success`() {
        val id = 1L
        val productGetResponse = ProductMother.createProductGetResponse()

        given(productService.findById(id)).willReturn(productGetResponse)

        mockMvc.perform(
                MockMvcRequestBuilders.get(productsUrlWithId, id)
        ).andExpect(status().isOk)
                .andExpect(jsonPath("$.name").value(productGetResponse.name))
                .andExpect(jsonPath("$.value").value(productGetResponse.value))

        verify(productService, times(1)).findById(id)
    }

    @Test
    fun `Given invalid id When findById Then return not found`() {
        val id = 1L

        given(productService.findById(id)).willThrow(NotFoundException(ExceptionMessage.PRODUCT_NOT_FOUND))

        mockMvc.perform(
                MockMvcRequestBuilders.get(productsUrlWithId, id)
        ).andExpect(status().isNotFound)

        verify(productService, times(1)).findById(id)
    }
}