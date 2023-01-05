package com.example.userservice.VO

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
    private val email: String,
    private val name: String,
    private val userId: String,
    private val orders: List<ResponseOrder>,
)