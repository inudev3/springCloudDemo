package com.example.userservice.dto

import com.example.userservice.NoArg
import com.example.userservice.entity.ResponseOrder
import java.time.LocalDateTime

@NoArg
data class UserDto(
    var email: String,
    var pwd: String,
    var name: String,
    var userId: String,
    var createdAt: LocalDateTime,
    var encryptedPwd: String,
    var orders:ArrayList<ResponseOrder>
)
