package com.example.orderservice.service


import com.example.orderservice.Mapper.ModelMapper
import com.example.orderservice.dto.OrderDto
import com.example.orderservice.entity.OrderEntity
import com.example.orderservice.repository.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(private val mapper: ModelMapper,private val orderRepository: OrderRepository):OrderService {


    override fun createOrder(orderDto: OrderDto): OrderDto {

        orderDto.orderId = UUID.randomUUID().toString()
        orderDto.totalPrice = orderDto.qty*orderDto.unitPrice
        val order =mapper.mapper<OrderDto, OrderEntity>(orderDto)

        orderRepository.save(order)
        return mapper.mapper(order)
    }

    override fun getOrdersByUserId(userId: String): Iterable<OrderDto> =
        orderRepository.findByUserId(userId).map { mapper.mapper(it) }


    override fun getOrderByOrderId(orderId: String): OrderDto =
        orderRepository.findByOrderId(orderId).let{mapper.mapper(it)}
}