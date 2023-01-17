package com.example.orderservice.controller

import com.example.orderservice.Mapper.ModelMapper
import com.example.orderservice.dto.OrderDto
import com.example.orderservice.kafka.CatalogProducer
import com.example.orderservice.kafka.OrderProducer
import com.example.orderservice.service.OrderService
import com.example.orderservice.vo.RequestOrder
import com.example.orderservice.vo.ResponseOrder
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID


@RestController
@RequestMapping("/order-service")
class OrderController(private val orderProducer: OrderProducer,private val catalogProducer: CatalogProducer, private val mapper: ModelMapper, private val orderService: OrderService) {
    val log = LoggerFactory.getLogger(OrderController::class.java)
    @PostMapping("/{userId}/orders")
    fun createOrder(
        @PathVariable("userId") userId: String,
        @RequestBody orderDetails: RequestOrder
    ): ResponseEntity<ResponseOrder> {
        log.info("Before add orders data")
        val orderDto = mapper.mapper<RequestOrder, OrderDto>(orderDetails).also { it.userId = userId }
        return orderDto.let(orderService::createOrder)
//            .also { it.orderId=UUID.randomUUID().toString(); it.totalPrice=orderDetails.qty*orderDetails.unitPrice }
            .let {
                log.info("After add orders data")
//                catalogProducer.send("example-catalog-topic", it)
//                orderProducer.send("orders", it)
                ResponseEntity.status(HttpStatus.OK).body(mapper.mapper(it))
            }
    }

    @GetMapping("/{userId}/orders")
    fun getOrder(@PathVariable("userId") userId: String): ResponseEntity<List<ResponseOrder>> =
        orderService.getOrdersByUserId(userId).map { mapper.mapper<OrderDto, ResponseOrder>(it) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it)     }
}
