package com.green.dto;

import com.green.entity.Article;

public class ArticleDto {

	// Field
	private String title;
	private String content;
	
	/*
	 * public ArticleDto() {} public ArticleDto(String title, String content) {
	 * super(); this.title = title; this.content = content; }
	 */
	
	// Getter / Setter
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
	
	// toString , 재정의
	@Override
	public String toString() {
		return "ArticleDto [title=" + title + ", content=" + content + "]";
	}
	
	// Article article = articleDto.toEntity(); 메소드
	public Article toEntity() {
		
		Article article = new Article( null, title, content );
		return article;
		
	}
	
}
