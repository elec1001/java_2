package com.practice.spring.catalogservice.domain;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;

@Builder
public record Book (
        @Id
        Long id,
        @NotBlank(message = "The Book ISBN must be defined")
        @Pattern(
                regexp ="(^[0-9]{10}|[0-9]{13})$",
                message ="The ISBN format must be valid."
        )
        String  isbn,
        @NotBlank(message = "The book author must be defined.")
        String author,
        @NotBlank(message = "The book title must be defined.")
        String title,
        @NotNull(message = "The book price must be defined.")
        @Positive(message = "The book price must be greater than 0")
        Double price,
        @CreatedDate
        @Column("create_at")
        Instant createdAt,
        @Column("last_modified_at")
        @LastModifiedDate
        Instant lastModifiedAt,
        @Version
        int version //낙관적 락 ? (혹은 비관적 락).동시성 문제 해결
){}
