package com.practice.spring.webfrontsrevice.client;

import com.practice.spring.webfrontsrevice.dto.CatalogListResponseDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogRequestDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "catalogClient", url = "${polar.edge-service-url}/books")
public interface CatalogClient {

    @GetMapping
    List<CatalogListResponseDTO> catalogList();

    @PostMapping
    CreateCatalogResponseDTO createCatalog(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody CreateCatalogRequestDTO createCatalogRequestDTO
    );

}