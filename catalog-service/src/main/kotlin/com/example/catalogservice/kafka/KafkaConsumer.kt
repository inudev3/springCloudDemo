package com.example.catalogservice.kafka

import com.example.catalogservice.Mapper.ModelMapper
import com.example.catalogservice.logger
import com.example.catalogservice.repository.CatalogRepository
import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer (private val mapper:ModelMapper,private val catalogRepository: CatalogRepository){
    val log=logger()
    @KafkaListener(topics = arrayOf("example-catalog-topic"))
    fun process(kafkaMessage:String){
        log.info(kafkaMessage)


        ObjectMapper().readValue(kafkaMessage, object:TypeReference<Map<Any,Any>>(){})?.let {map->
            val productId = map["productId"] as? String ?: throw RuntimeException("casting failed")
            catalogRepository.findByProductId(productId).also{it.stock-=(map["qty"] as? Int)?: throw RuntimeException("cast failed")}

        }


    }
}