package com.example.orderservice.kafka

import com.example.orderservice.dto.OrderDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class CatalogProducer(private val kafkaTemplate: KafkaTemplate<String,String>) {
    val log = LoggerFactory.getLogger(CatalogProducer::class.java)
    fun send(topic:String, orderDto: OrderDto):OrderDto{
        log.info(topic)
        log.info("orderDto: $orderDto")
        try{
            var str=ObjectMapper().let {
                it.writeValueAsString(orderDto)
            }
            kafkaTemplate.send(topic,str)
        }catch (ex:JsonProcessingException){ex.printStackTrace()}
        return orderDto
    }
}