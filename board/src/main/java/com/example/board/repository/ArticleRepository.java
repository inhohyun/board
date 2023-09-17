package com.example.board.repository;

import com.example.board.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArticleRepository  extends CrudRepository<Article, Long> {

    // 모든 데이터 찾기


    @Override
    List<Article> findAll();
}
