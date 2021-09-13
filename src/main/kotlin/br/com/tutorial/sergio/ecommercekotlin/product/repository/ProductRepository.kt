package br.com.tutorial.sergio.ecommercekotlin.product.repository

import br.com.tutorial.sergio.ecommercekotlin.product.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Long>