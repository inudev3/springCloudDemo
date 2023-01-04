package com.example.userservice.entity

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
    private val email: String? = null,
    private val name: String? = null,
    private val userId: String? = null,
    private val orders: List<ResponseOrder>? = null,
)