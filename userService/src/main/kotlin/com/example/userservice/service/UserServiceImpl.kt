package com.example.userservice.service


import com.example.userservice.Mapper.ModelMapper
import com.example.userservice.dto.UserDto
import com.example.userservice.entity.UserEntity
import com.example.userservice.repository.UserRepository
import lombok.RequiredArgsConstructor
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder


import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@RequiredArgsConstructor
@Service
class UserServiceImpl(
    private val mapper: ModelMapper,
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) : UserService {


    override fun getUserDetailsByEmail(email: String) =
        userRepository.findByEmail(email)?.let { mapper.mapper<UserEntity, UserDto>(it) }
            ?: throw UsernameNotFoundException("User Not Found")


    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity = username?.let { userRepository.findByEmail(it) } ?: throw UsernameNotFoundException("User Not Found")

        return User(userEntity.email, userEntity.encryptedPwd, true, true, true, true, mutableListOf())
    }

    override fun getUserByUserId(userId: String): UserDto {
        val userEntity = userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User Not Found")
        val userDto = userEntity.let {
            mapper.mapper<UserEntity, UserDto>(it)
        }.also { it.orders = ArrayList() }

        return userDto
    }

    override fun getUserByAll(): Iterable<UserEntity> {
        return userRepository.findAll()
    }

    override fun createUser(userDto: UserDto): UserDto {
        userDto.userId = UUID.randomUUID().toString()

        val user = mapper.mapper<UserDto, UserEntity>(userDto)
        user.encryptedPwd = passwordEncoder.encode(userDto.pwd)
        userRepository.save(user)
        return mapper.mapper(user)
    }
}
