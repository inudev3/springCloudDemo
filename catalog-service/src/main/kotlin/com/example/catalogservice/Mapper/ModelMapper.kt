package com.example.catalogservice.Mapper

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import kotlin.reflect.KParameter



class ModelMapper {


    inline fun <reified T, reified R> mapper(source:T):R{
        R::class.constructors.last{constructor->
            val map = mutableMapOf<KParameter,Any?>()
            constructor.parameters.forEach { param->
                map[param] = T::class.members.find { param.name==it.name }?.call(source)
            }
            return constructor.callBy(map)
        }
        throw RuntimeException("${R::class.simpleName} 이름의 생성자가 존재하지 않습니다")
    }
}