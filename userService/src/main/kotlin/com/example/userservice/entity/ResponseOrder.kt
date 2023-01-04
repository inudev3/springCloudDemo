package com.example.userservice.entity

import java.util.*

data class ResponseOrder(
    val productId: String? = null,
    val qty: Int? = null,
    val unitPrice: Int? = null,
    val totalPrice: Int? = null,
    val createdAt: Date? = null,
    val orderId: String? = null,
)