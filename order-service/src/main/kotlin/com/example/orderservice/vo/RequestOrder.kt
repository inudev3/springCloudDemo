package com.example.orderservice.vo


data class RequestOrder (
    var productId: String,
    var qty: Int,
    var unitPrice: Int,
)