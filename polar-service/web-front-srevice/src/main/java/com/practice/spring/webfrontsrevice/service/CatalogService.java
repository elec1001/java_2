package com.practice.spring.webfrontsrevice.service;

import com.practice.spring.webfrontsrevice.client.CatalogClient;
import com.practice.spring.webfrontsrevice.dto.CatalogListResponseDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogRequestDTO;
import com.practice.spring.webfrontsrevice.dto.CreateCatalogResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogService {

    private final CatalogClient catalogClient;

    public List<CatalogListResponseDTO> catalogList() {
        return catalogClient.catalogList();
    }

    public CreateCatalogResponseDTO createCatalog(
            String accessToken,
            CreateCatalogRequestDTO createCatalogRequestDTO
    ) {

        return catalogClient.createCatalog(accessToken,createCatalogRequestDTO);

    }
}
