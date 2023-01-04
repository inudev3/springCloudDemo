package com.example.orderservice.dto

data class OrderDto(
    var productId: String,
    var qty: Int,
    var unitPrice: Int,
    var totalPrice: Int,
    var orderId: String,
    var userId: String
)