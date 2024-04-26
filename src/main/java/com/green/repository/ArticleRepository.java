package com.green.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.green.dto.ArticleForm;
import com.green.entity.Article;

public interface ArticleRepository
		extends CrudRepository<Article, Long> {

	// ArticleController 132 line 형변환 해결방법
	// 상속관계를 이용하여 List를 Iterable 로 UpCasting 하여 형변환 되지 않음
	// Iterable(I) <- Collection(C) <- List(I) <- ArrayList(C)
	// 아래 내용 추가
	@Override
	ArrayList<Article> findAll() ;

	Article save(ArticleForm dto);
	
	// List<Article> articleEntityList = articleRepository.findAll();
	// ArticleController 132 line 에 .findAll() 이 Iterable type -> 여기서 fineAll() 을 ArrayList type 으로 Down Casting -> 재정의 
	// alt + shift + s -> override
	
}
