package com.example.orderservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseOrder (
    var productId: String,
    var qty: Int,
    var unitPrice: Int,
    var totalPrice: Int?,
    var userId: String?,
    var createdAt: Date?
)