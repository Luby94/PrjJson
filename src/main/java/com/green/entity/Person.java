package com.green.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

	private Long id;
	private String name;
	private int age;
	private List<String> hobbies;
	
}
