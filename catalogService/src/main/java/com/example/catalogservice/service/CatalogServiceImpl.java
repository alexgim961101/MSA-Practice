package com.example.catalogservice.service;

import com.example.catalogservice.domain.Catalog;
import com.example.catalogservice.domain.CatalogRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService{
    private final CatalogRepo catalogRepo;
    @Override
    public List<Catalog> getAllCatalogs() {
        return catalogRepo.findAll();
    }
}
