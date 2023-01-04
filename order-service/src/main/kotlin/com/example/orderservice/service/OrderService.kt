package com.example.orderservice.service

import com.example.orderservice.dto.OrderDto

interface OrderService {
    fun createOrder(orderDto: OrderDto):OrderDto
    fun getOrdersByUserId(userId:String):Iterable<OrderDto>
    fun getOrderByOrderId(orderId:String):OrderDto
}