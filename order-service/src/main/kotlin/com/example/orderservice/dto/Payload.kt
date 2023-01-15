package com.example.orderservice.dto

import com.example.orderservice.NoArg


@NoArg
class Payload(
    order_id:String?,
    user_id:String?,
    product_id:String?,
    qty:Int,
    unit_price:Int,
    total_price:Int?
) {
    var order_id:String?=order_id
    var user_id:String?=user_id
    var product_id:String?=product_id
    var qty:Int=qty
    var unit_price:Int=unit_price
    var total_price:Int?=total_price
}