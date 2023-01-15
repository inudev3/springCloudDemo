package com.example.catalogservice.controller

import com.example.catalogservice.Mapper.ModelMapper
import com.example.catalogservice.entity.CatalogEntity
import com.example.catalogservice.service.CatalogService
import com.example.catalogservice.vo.ResponseCatalog
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class CatalogController (val mapper: ModelMapper, val environment: Environment, val catalogService: CatalogService){
    @GetMapping("/health-check")
    fun status():String{

        return "health Check"
    }
    @GetMapping("/catalogs")
    fun getCatalogs(): ResponseEntity<List<ResponseCatalog>> =catalogService.getAllCatalogs().map{
        mapper.mapper<CatalogEntity,ResponseCatalog>(it)
    }.let{ ResponseEntity.status(HttpStatus.OK).body(it)}

}