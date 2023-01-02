package com.example.userservice.service


import com.example.userservice.Mapper.ModelMapper
import com.example.userservice.dto.UserDto
import com.example.userservice.entity.UserEntity
import com.example.userservice.repository.UserRepository
import lombok.RequiredArgsConstructor



import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@RequiredArgsConstructor
@Service
class UserServiceImpl(private val mapper: ModelMapper, private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder):UserService {


    override fun createUser(userDto: UserDto){
        userDto.userId = UUID.randomUUID().toString()

        val user =mapper.mapper<UserDto, UserEntity>(userDto)
        user.encryptedPwd = passwordEncoder.encode(userDto.pwd)
        userRepository.save(user)
    }
}
