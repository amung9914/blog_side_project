package com.blog.controller;

import com.blog.domain.Article;
import com.blog.dto.ArticleListViewResponse;
import com.blog.dto.ArticleViewResponse;
import com.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogViewController {

    private final BlogService blogService;

    /**
     * 글목록 화면
     */
    @GetMapping("/articles")
    public String getArticles(Model model){
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles",articles);

        return "articleList";
    }
    /**
     * 글 세부내용 화면
     */
    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model){
        Article article = blogService.findById(id);
        model.addAttribute("article",new ArticleViewResponse(article));

        return "article";
    }

    /**
     * 글 작성 또는 수정 화면
     */
    @GetMapping("/new-article")
    // id는 없을 수도 있다고 명시
    public String newArticle(@RequestParam(required = false) Long id, Model model){
        if(id==null){
            // 생성
            model.addAttribute("article", new ArticleViewResponse());
        }else{
            // 수정
            Article article = blogService.findById(id);
            model.addAttribute("article",new ArticleViewResponse(article));
        }

        return "newArticle";
    }
}
