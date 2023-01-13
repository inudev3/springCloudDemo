package com.example.catalogservice.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault

import java.io.Serializable
import java.util.*

@Entity
@Table(name="catalog")
class CatalogEntity(
    id:Long,
    productId:String,
    productName:String,
    stock:Int,
    unitPrice:Int,
    createdAt:Date,

) :Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=id
    @Column(nullable = false)
    var productId:String=productId

    @Column(nullable = false)
    var productName:String=productName

    var stock:Int=stock
    var unitPrice:Int = unitPrice

    @ColumnDefault(value="CURRENT_TIMESTAMP")
    var createdAt: Date =createdAt
}