package com.green.dto;

import com.green.entity.Article;

public class ArticleForm {
	
	private  Long    id;  // 수정을 위해 추가
	private  String  title;
	private  String  content;
	
	//getter /setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	// 생성자
	public ArticleForm(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	// toString
	@Override
	public String toString() {
		return "ArticleForm [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	// Method
	// 클래스 쓰는 방법 : 데이터 중심의 클래스(Vo etc), 기능 중심의 클래스(프로레스, 컨트롤러 etc)
	public  Article  toEntity( ) {
		
		return  new Article(id, title, content);
		
	}
	
}




