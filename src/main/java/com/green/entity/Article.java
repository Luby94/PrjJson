package com.green.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// 실제 database 의 table 구조를 만든다 : Create Table
// import : jakarta 로 -> import jakarta.persistence.
// persistence : 영속성 -> 영구적으로 보관, pk(primary key) 를 영구적으로 만들어라
@Entity
public class Article {

	// primary key : @Id
	// 값을 자동으로 채워라 : @GeneratedValue
	// @GeneratedValue() 에 ctrl+space : strategy= + 선택가능
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	
	// Constructor
	public Article() {}
	public Article(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	// Getter / Setter
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
	
	// toString
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	
}

// Entity class 를 생성한 것만으로도 db 에 table 이 만들어짐
