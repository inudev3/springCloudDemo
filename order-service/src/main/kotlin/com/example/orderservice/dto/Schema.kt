package com.example.orderservice.dto



class Schema(
    type:String,
    fields:MutableList<Field>,
    optional:Boolean,
    name:String,
){
    var type:String= type
    val fields:MutableList<Field> = fields
    var optional:Boolean = optional
    var name:String=name
}