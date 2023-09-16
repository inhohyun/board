package com.example.board.dto;


import com.example.board.entity.Article;

//form 데이터를 받아올 dto
public class ArticleForm {

    private Long id;
    private String title;
    private String content;

    public ArticleForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }


    @Override
    public String toString() {
        return "ArticleForm{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Article toEntity(){

        return new Article(id,title, content);
    }
}
