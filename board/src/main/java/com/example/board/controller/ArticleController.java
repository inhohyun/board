package com.example.board.controller;

import com.example.board.dto.ArticleForm;
import com.example.board.entity.Article;
import com.example.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    // new로 객체를 생성하는 것이 아닌 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동연결
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticle(){

        return "articles/new";
    }


    //게시글 생성
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm) {
    // 받아온 데이터 db에 저장하기
        System.out.println(articleForm.toString());
    //1. dto를 entity로 변환
        Article article = articleForm.toEntity();

    //2. 엔티티를 레파지토리를 활용해 db에 저장
    // 레파지토리에 존재하는 save 메소드를 활용해서 db에 저장
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }

}
