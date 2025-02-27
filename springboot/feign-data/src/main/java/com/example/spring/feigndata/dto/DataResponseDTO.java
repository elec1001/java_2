package com.example.feigndata.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder //?
@AllArgsConstructor //생성자
public class DataResponseDTO {
   private Long id;
   private String name;
   private String value;
}
