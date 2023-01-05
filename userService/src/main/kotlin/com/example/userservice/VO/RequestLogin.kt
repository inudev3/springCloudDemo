package com.example.userservice.VO

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


data class RequestLogin(
   @NotNull(message = "Email cannot be null")
    @Size(min=2, message = "Email cannot be less than two characters")
    @Email
    var email:String,

   @NotNull(message = "Password cannot be null")
   @Size(min=8, message = "Password cannot be less than two characters")
   @Email
   var password:String,
)