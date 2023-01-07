package com.example.catalogservice.service

import com.example.catalogservice.entity.CatalogEntity

interface CatalogService {
    fun getAllCatalogs():Iterable<CatalogEntity>
}