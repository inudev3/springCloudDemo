package com.example.orderservice.entity

import jakarta.persistence.*
import java.io.Serializable
import java.util.Date


@Entity
@Table(name="orders")
class OrderEntity (
    productId:String,
    qty:Int,
    unitPrice:Int,
    totalPrice:Int,
    userId:String,
    orderId:String,
    createdAt:Date
):Serializable{//직렬화의 목적은 데이터베이스 보관하기 위함
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var Id:Long?=null
    @Column(nullable = false)
    var productId=productId
    @Column(nullable = false)
    var qty=qty
    var unitPrice=unitPrice
    @Column(nullable = false)
    var totalPrice=totalPrice
    @Column(nullable = false, unique = true)
    var orderId=orderId
    @Column(nullable = false)
    var userId=userId
    @Column(nullable = false, updatable = false, insertable = false)
    var createdAt=createdAt
}