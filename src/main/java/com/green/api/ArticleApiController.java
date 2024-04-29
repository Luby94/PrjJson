package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.service.ArticleService;

@RestController
public class ArticleApiController {
	
	@Autowired
	private ArticleService articleService;
	//private ArticleRepository articleRepository;

	// GET List : 목록 조회
	// 브라우저에 요 주소("/api/articles")가 들어오면 아래 함수를 실행해라
	@GetMapping(value="/api/articles", produces=MediaType.APPLICATION_JSON_VALUE)	// produces="application/xml;charset=utf-8", jackson 라이브러리
	public List<Article> index() {	// public 뒤 : List<Article> 을 return 시키겠다
		
		//return articleRepository.findAll();	// articleRepository : mapper 역할 -> 위에 @Autowired
		return articleService.index();
		
		// localhost:9090/api/articles → json 형태로 목록에 있는 데이터 조회
		// Service : 비지니스 로직, 일반적으로 DB 와 관련없는 로직을 처리하기 위해 만드는 부분
		// Repository : DB 조작하는거 -> Controller 에서 Service 로 옮김
		// Controller 함수형 언어 : 10 ~ 20줄 내의 코딩만 넣기 : 단위형 function => 일(기능) 쪼개라
		// Service : 비지니스 로직 <-> Repository : Dto
		
	}
	
	// GET ID : ID 로 조회
	@GetMapping("/api/articles/{id}")
	public Article show(@PathVariable Long id) {
		
		Article article = articleService.show(id);
		
		return article;
		
	}
	
	// POST : insert (추가) - create
	// 결과 : 저장된 article 객체, 상태코드 <- 저장되었습니다
	// ResponseEntity<T>
	// Generic : 파라미터 type 을 객체 (T) type 을 사용해라  
	// <T> : class type 의 type 의 약자 T , <?> T 는 외부에 입력된 type
	// ResponseEntity<Article> : 응답 데이터, 돌려주는 값, 2가지 데이터 돌려줌
	//  = Article Data + http state code : 200 = ResponseEntity
	// json 형태 -> {"id":1, "title":"새글", "content"="새글 내용"} -> 400 에러, id 존재, 사용자 정의 에러(user defined)
	// {"title":"새글", "content"="새글 내용"} -> 200
	// .body(null) == .build()
	// @RequestBody : json 으로 넘어옴, json string 으로 넘어오는 값을 java 의 객체(ArticleForm)로 저장
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create( @RequestBody ArticleForm dto ) {
		
		Article created = articleService.create( dto );		// id 가 존재하면 return null; 에 따라 created 가 null 이됨
		ResponseEntity<Article> result
		  = ( created != null )
		  ? ResponseEntity.status(HttpStatus.OK).body(created)
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	// BAD_REQUEST : 400 error
		
		return result;
		
	}
	
	// PATCH : update (수정)
	@PatchMapping("/api/articles/{id}")
	public ResponseEntity<Article> update( @PathVariable Long id, @RequestBody ArticleForm dto ) {
		
		System.out.println( "id:" + id + ", dto:" + dto );
		// id:1, dto:ArticleForm(id=null, title=eee, content=eee) -> dto : id null 로 인식
		
		// apitest.html 의 patch 부분, params 의 body 부분, id 를 PathVariable 부분으로 받음
		Article updated = articleService.update( id, dto );
		ResponseEntity<Article> result
		  = ( updated != null )
		  ? ResponseEntity.status(HttpStatus.OK).body(updated)
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
		
	}
	
	// DELETE : delete
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Article> delete( @PathVariable Long id, @RequestBody ArticleForm dto ) {
		
		System.out.println( "id:" + id + ", dto:" + dto );
		// id:1, dto:ArticleForm(id=null, title=eee, content=eee) -> dto : id null 로 인식
		
		// apitest.html 의 patch 부분, params 의 body 부분, id 를 PathVariable 부분으로 받음
		Article deleted = articleService.delete( id );
		ResponseEntity<Article> result
		  = ( deleted != null )
		  ? ResponseEntity.status(HttpStatus.NO_CONTENT).build()
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
		
	}
	
	/*
	  [
        { title : "시간 예약"   , content : "1240" },
    	{ title : "테이블 지정" , content : "A12" },
    	{ title : "메뉴 선택"   , content : "Branch A" }
      ]
	// 객체 데이터 3개가 모여있는 객체 배열
	*/
	// Transaction : 세 개의 data 받아서 서비스 함수에 넘겨주고 결과 받는다
	@PostMapping("/api/transaction-test")
	public ResponseEntity<List<Article>> transactionTest(
			@RequestBody List<ArticleForm> dtos
			) {
		
		List<Article> createdList = articleService.createArticles(dtos);
		
		ResponseEntity<List<Article>> result
		  = ( createdList != null )
		  ? ResponseEntity.status(HttpStatus.OK).body(createdList)
		  : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
		
	}

	@PostMapping("/api/transaction-test2")
	public ResponseEntity<List<Article>> transactionTest2(
			@RequestBody List<ArticleForm> dtos
			) {
		
		List<Article> createdList = articleService.createArticles2(dtos);
		
		ResponseEntity<List<Article>> result
		= ( createdList != null )
		? ResponseEntity.status(HttpStatus.OK).body(createdList)
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		return result;
		
	}
	
}















