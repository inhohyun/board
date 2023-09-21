package com.example.board.controller;

import com.example.board.dto.ArticleForm;
import com.example.board.entity.Article;
import com.example.board.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
// 프로젝트 중단, 프리코스 후 다시 돌아올 것
@Controller
public class ArticleController {
    // new로 객체를 생성하는 것이 아닌 스프링 부트가 미리 생성해 놓은 객체를 가져다가 자동연결
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticle() {

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
        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("articles/{id}")
    private String show(@PathVariable Long id, Model model) {

//        1. id를 조회해 db에서 해당 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

//        2. 가져온 데이터를 모델에 등록하기
        //model 사용법 -> model.addAttribute(String name, Object value); -> 이름과 값으로 저장
        model.addAttribute("article", articleEntity);


//        3. 조회한 데이터를 사용자에게 보여 주기 위한 뷰 페이지 만들고 반환하기
        return "articles/show";
    }

    @GetMapping("/articles")
    private String index(Model model) {
        //1. 모든 데이터 가져오기
        List<Article> articleEntityList = articleRepository.findAll();
        //2. 모델에 데이터 등록하기
        model.addAttribute("articleList", articleEntityList);
        //3. 뷰 페이지 설정하기

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    private String edit(@PathVariable Long id, Model model) {
        //수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        return "articles/edit";

    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        //1. dto를 엔티티로 변환한다.
        Article articleEntity = form.toEntity();
        //2. 엔티티를 db로 저장한다.
        //2-1. db에 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터가 있다면 값을 갱신한다.
        if (target != null) {
            articleRepository.save(articleEntity); // form에서 받아온 데이터로 저장
        }
        //3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:" + articleEntity.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable Long id) {
        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        //2. 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
        }
        //3. 결과 페이지로 리다이렉트 한다.
        return "/articles";
    }

}
