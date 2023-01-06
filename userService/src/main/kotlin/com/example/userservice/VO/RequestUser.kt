package com.example.userservice.VO


import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

import java.time.LocalDateTime


data class RequestUser(
    @NotNull(message = "Email cannot be null")
    @Email
    var email: String,

    @NotNull(message = "Password cannot be null")
    @Size(min = 8)
    var pwd: String,

    @NotNull(message = "name cannot be null")
    @Size(min = 2)
    var name: String


)

