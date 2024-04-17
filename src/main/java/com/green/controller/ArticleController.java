package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;

	@GetMapping("/articles/WriteForm")
	public String writeForm() {
		
		return "articles/write";
		
	}
	
	//-----------------------------------------------------------------------------------------------------------	
	
	/*
	@GetMapping("articles/Write")
	//public String write( @RequestParam("title") String title, @RequestParam("content") String content ) {
	//public String write( String title, String content ) {
	public String write( ArticleDto articleDto ) {
		
		System.out.println( articleDto );	
		
		return "redirect:/articles/List";
	}
	*/
	
	//-----------------------------------------------------------------------------------------------------------
	
	// @GetMapping("articles/Write")
	// 405 error : method="post" 인데, 받는놈이 @GetMapping 으로 받아서 나는 에러
	// Post 로 던지는 놈은 @PostMapping 으로 받자
	// .mustache 에서 보내는 name="title" , name="content" 를 Controller 에 보냄(form tag, submit)
	// Controller 에서는 변수 두개를 써서 받지 않고 ArticleDto 를 생성해서 ArticleDto 하나로만 받음
	@PostMapping("articles/Write")
	public String write( ArticleDto articleDto ) {

		// 넘어온 data 확인
		System.out.println( articleDto );	// 교재 : ArticleForm
		// Console : ArticleDto [title=aaa, content=에이에이에이] -> 값 전달받고 있음
		// 1. http://localhost:9090/articles/WriteForm 에서 입력한 것이 ArticleDto 에 저장
		// 2. Controller 에서 ArticleDto 를 받음
		// 3. sysout 로 Console 확인해보니 http://localhost:9090/articles/WriteForm 에서 입력한 Data 를 전달받음
		
		// db 에 저장 = h2 article 테이블에 저장
		// 1. Dto -> Entity 로 보냄
		// 2. Entity -> Repository 로 보내서 저장
		// Entity : db 의 테이블 이다 -> mapper.xml 과 같은 역할
		//-----------------------------------------------------------------------------------------
		// 1. Dto -> Entity 로 보냄
		Article article = articleDto.toEntity();
		
		// 2. Repository(인터페이스)를 사용하여 Entity 를 저장
		Article saved = articleRepository.save( article );
		
		return "redirect:/articles/List";
		
	}
	
}
