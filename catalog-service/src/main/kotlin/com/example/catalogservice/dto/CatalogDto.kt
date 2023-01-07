package com.example.catalogservice.dto

data class CatalogDto(
    var productId: String,
    var qty: Int,
    var unitPrice: Int,
    var totalPrice: Int,
    var orderId: String,
    var userId: String
)