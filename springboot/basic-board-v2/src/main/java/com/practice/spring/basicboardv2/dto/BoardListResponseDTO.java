package com.practice.spring.basicboardv2.dto;

import com.practice.spring.basicboardv2.model.Article;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class BoardListResponseDTO {
    List<Article> articles;
    boolean last;
}
