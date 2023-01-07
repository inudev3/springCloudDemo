package com.example.catalogservice.service

import com.example.catalogservice.entity.CatalogEntity
import com.example.catalogservice.repository.CatalogRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class CatalogServiceImpl(private val  catalogRepository:CatalogRepository):CatalogService {

    override fun getAllCatalogs(): Iterable<CatalogEntity> {
        return catalogRepository.findAll()
    }
}