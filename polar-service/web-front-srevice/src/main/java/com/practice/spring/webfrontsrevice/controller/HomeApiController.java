package com.practice.spring.webfrontsrevice.controller;

import com.practice.spring.webfrontsrevice.dto.CatalogListResponseDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogRequestDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogResponseDTO;
import com.practice.spring.webfrontsrevice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webs/api/catalog")
public class HomeApiController {

    private final CatalogService catalogService;

    @GetMapping
    public List<CatalogListResponseDTO> catalogList() {
        return catalogService.catalogList();
    }

    @PostMapping
    public CreateCatalogResponseDTO createCatalog(
            @RequestHeader(value = AUTHORIZATION, required = false) String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO){
        return catalogService.createCatalog(accessToken,createCatalogRequestDTO);
    }
}
