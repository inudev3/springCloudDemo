package com.example.orderservice.dto

import com.example.orderservice.NoArg
import java.io.Serializable


@NoArg
class KafkaOrderDto (
    val schema: Schema,
    val payload: Payload
):Serializable