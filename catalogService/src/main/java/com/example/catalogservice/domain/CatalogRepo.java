package com.example.catalogservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogRepo extends JpaRepository<Catalog, Long> {
    Optional<Catalog> findByProductId(String productId);
}
