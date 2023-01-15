package com.example.orderservice.dto

import com.example.orderservice.NoArg

@NoArg
class Field(
    type:String,
    optional:Boolean,
    field:String
){
    var type:String=type
    var optional:Boolean=optional
    var field:String=field
}