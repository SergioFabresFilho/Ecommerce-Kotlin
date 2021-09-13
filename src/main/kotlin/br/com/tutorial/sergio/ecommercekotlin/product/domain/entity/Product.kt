package br.com.tutorial.sergio.ecommercekotlin.product.domain.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Product(

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long?,
        val name: String,
        val value: Double
)