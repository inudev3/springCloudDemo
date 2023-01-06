package com.example.userservice.dto

import com.example.userservice.NoArg
import com.example.userservice.VO.ResponseOrder
import java.util.Date

@NoArg
data class UserDto(
    var email: String,
    var pwd: String?,
    var name: String,
    var userId: String?,
    var createdAt: Date?=null,
    var encryptedPwd: String?=null,
    var orders:ArrayList<ResponseOrder>?=null
)
