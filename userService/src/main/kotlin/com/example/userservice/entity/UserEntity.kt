package com.example.userservice.entity

import jakarta.persistence.*

@Entity
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null ,
    email:String,
    name:String,
    userId:String,
    encryptedPwd:String
)
{
    @Column(nullable = false)
    var email: String?=email
    @Column( nullable = false, length = 50)
    var name: String?   =name


    var userId: String?=userId


    var encryptedPwd: String?=encryptedPwd

}


