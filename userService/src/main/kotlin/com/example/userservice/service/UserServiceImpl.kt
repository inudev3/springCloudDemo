package com.example.userservice.service


import com.example.userservice.Mapper.ModelMapper
import com.example.userservice.dto.UserDto
import com.example.userservice.entity.UserEntity
import com.example.userservice.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UsernameNotFoundException


import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@RequiredArgsConstructor
@Service
class UserServiceImpl(private val mapper: ModelMapper, private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder):UserService {

    override fun getUserByUserId(userId: String): UserDto {
        val userEntity=userRepository.findByUserId(userId)?:throw UsernameNotFoundException("User Not Found")
        val userDto=userEntity.let{
            mapper.mapper<UserEntity,UserDto>(it)
        }.also { it.orders=ArrayList() }

        return userDto
    }

    override fun getUserByAll(): Iterable<UserEntity> {
        return userRepository.findAll()
    }

    override fun createUser(userDto: UserDto){
        userDto.userId = UUID.randomUUID().toString()

        val user =mapper.mapper<UserDto, UserEntity>(userDto)
        user.encryptedPwd = passwordEncoder.encode(userDto.pwd)
        userRepository.save(user)
    }
}
