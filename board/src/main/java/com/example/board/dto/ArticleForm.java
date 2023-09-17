package com.example.board.dto;


import com.example.board.entity.Article;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
//form 데이터를 받아올 dto
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public Article toEntity(){

        return new Article(id,title, content);
    }
}
