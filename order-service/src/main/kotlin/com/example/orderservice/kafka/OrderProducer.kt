package com.example.orderservice.kafka

import com.example.orderservice.dto.*
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import kotlin.reflect.full.declaredMemberProperties

@Service
class OrderProducer(private val kafkaTemplate: KafkaTemplate<String, String>) {
    val log = LoggerFactory.getLogger(OrderProducer::class.java)
    val fields = Payload::class.declaredMemberProperties.map {

        Field(
            when (it.returnType.classifier) {
                String::class -> "string"
                Int::class -> "int32"
                else -> ""
            }, it.returnType.isMarkedNullable, it.name
        )
    }.toMutableList()
    val schema = Schema(
        type = "struct",
        fields = fields,
        optional = false,
        name = "orders"
    )

    fun send(topic: String, orderDto: OrderDto): OrderDto {

        val payload = Payload(
            order_id = orderDto.orderId,
            user_id = orderDto.userId,
            product_id = orderDto.productId,
            unit_price = orderDto.unitPrice,
            total_price = orderDto.totalPrice,
            qty = orderDto.qty
        )

        try {
            var str = ObjectMapper().let {
                it.writeValueAsString(KafkaOrderDto(schema = schema, payload = payload))
            }
            log.info(topic)
            log.info(str)
            kafkaTemplate.send(topic, str)
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }
        return orderDto
    }
}