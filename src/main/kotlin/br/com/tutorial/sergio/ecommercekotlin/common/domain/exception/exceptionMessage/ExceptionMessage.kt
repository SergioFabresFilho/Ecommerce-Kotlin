package br.com.tutorial.sergio.ecommercekotlin.common.domain.exception.exceptionMessage

enum class ExceptionMessage(
        val message: String
) {
    PRODUCT_NOT_FOUND("Produto não encontrado");

    override fun toString(): String {
        return message
    }
}