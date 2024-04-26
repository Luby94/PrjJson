package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	//------------------------------------------
	// Service 는 Controller 가 아니기 때문에 RequestMapping, GetMapping 할 필요 없음
	
	// article 목록 조회
	public List<Article> index() {
		
		// DB 저장하기 전 작업할 코딩 넣는다
		return articleRepository.findAll();
	}

	//------------------------------------------	

	// article id 로 조회
	public Article show(Long id) {
		
		Article article = articleRepository.findById(id).orElse(null);
		
		return article;
	}

	//------------------------------------------
	
	public Article create(ArticleForm dto) {
		
		// 입력 data dto : {"title":"새글", "content":"새글 내용"}
		Article article = dto.toEntity();
		
		// create 는 생성요청이고 번호 자동증가이므로 번호 필요없다
		// 그래서 id 가 존재하면 안된다
		if( article.getId() != null )
			return null;
		
		// Article saved = articleRepository.save(dto);	// error : ArticleForm is not Entity
		Article saved = articleRepository.save(article);
		
		return saved;
	}

}
