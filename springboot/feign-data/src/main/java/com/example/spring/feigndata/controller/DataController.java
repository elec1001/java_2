package com.example.feigndata.controller;

import com.example.feigndata.DTO.DataResponseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController //@component를 포함하고 있으며 스피링에서 관리하게 됨(스피링 컨테이너)
@RequestMapping
public class DataController {

    private Map<Long, DataResponseDTO> dataStore=new HashMap<>();
    private Long idCnt=1L;
   // 초기 데이터를 추가하는 메서드
    @PostConstruct
    public void initDataSource(){
        dataStore.put(idCnt++, new DataResponseDTO(1L,"data 1","100"));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"data 2","200"));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"data 3","300"));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"data 4","400"));
        dataStore.put(idCnt++, new DataResponseDTO(1L,"data 5","500"));
    }

    @GetMapping("/{id}")
    public DataResponseDTO getById(@PathVariable Long id){
        return dataStore.get(id);

    }

}
