package com.example.catalogservice.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.Date

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseCatalog (
    var productId: String,
    var productName: String,
    var unitPrice: Int,
    var stock: Int,
    var createdAt:Date
)