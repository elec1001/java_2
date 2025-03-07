package com.practice.spring.basicboardv2.service;

import com.practice.spring.basicboardv2.mapper.BoardMapper;
import com.practice.spring.basicboardv2.model.Article;
import com.practice.spring.basicboardv2.model.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper;
    private final FileService fileService;
    
    public List<Article> getBoardArticles(int page,int size){
        //페이지는 1부터 시작 offset 계산
        int  offset = (page - 1) * size;
        return boardMapper.getBoardArticles(
                Paging.builder()
                        .offset(offset)
                        .size(size)
                        .build()
        );
    }
            
    

    @Transactional
    public void saveArticle(String userId, String title, String content, MultipartFile file) {
        String path=null;
        if(!file.isEmpty()){
            path=fileService.fileUpload(file);
        }

        boardMapper.saveArticle(
                Article.builder()
                        .title(title)
                        .content(content)
                        .userId(userId)
                        .filePath(path)
                        .build()
        );
    }


    public int getTotalArticleCnt() {
        return boardMapper.getArticleCnt();
    }
}
