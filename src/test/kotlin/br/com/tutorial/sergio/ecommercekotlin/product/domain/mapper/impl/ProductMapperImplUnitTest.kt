package br.com.tutorial.sergio.ecommercekotlin.product.domain.mapper.impl

import br.com.tutorial.sergio.ecommercekotlin.product.domain.mother.ProductMother
import org.assertj.core.api.BDDAssertions.then
import org.junit.jupiter.api.Test

class ProductMapperImplUnitTest {

    private val productMapper = ProductMapperImpl()

    @Test
    fun `Given valid ProductCreateRequest When toProduct Then return Product`() {
        val productCreateRequest = ProductMother.createProductCreateRequest()

        val product = productMapper.toProduct(productCreateRequest)

        then(product.id).isNull()
        then(product.name).isEqualTo(productCreateRequest.name)
        then(product.value).isEqualTo(productCreateRequest.value)
    }

    @Test
    fun `Given valid Product When toProductGetResponse Then return ProductGetResponse`() {
        val product = ProductMother.createProduct()

        val productGetResponse = productMapper.toProductGetResponse(product)

        then(productGetResponse.name).isEqualTo(product.name)
        then(productGetResponse.value).isEqualTo(product.value)
    }
}