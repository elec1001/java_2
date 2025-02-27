package com.example.spring.feigndata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataRequestDTO {
 private Long id;
 private  String name;
 private  int value;
}
