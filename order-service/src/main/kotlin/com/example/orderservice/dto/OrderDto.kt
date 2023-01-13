package com.example.orderservice.dto

import com.example.orderservice.NoArg


@NoArg
data class OrderDto(
    var productId: String,
    var qty: Int,
    var unitPrice: Int,
    var totalPrice: Int?,
    var orderId: String?,
    var userId: String?
)