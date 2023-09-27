package com.example.catalogservice.web.controller;

import com.example.catalogservice.domain.Catalog;
import com.example.catalogservice.service.CatalogService;
import com.example.catalogservice.web.dto.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/catalog-service")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    private final Environment env;

    @GetMapping("/health_check")
    public String status() {
        return String.format("It's working in catalog service on port %s", env.getProperty("local.server.port"));
    }

    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs() {
        List<Catalog> allCatalogs = catalogService.getAllCatalogs();

        List<ResponseCatalog> result = new ArrayList<>();
        for(Catalog c : allCatalogs) {
            result.add(ResponseCatalog.createResponse(c));
        }

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
