package com.green.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.entity.Person;

@Controller
public class ThymeleafExampleController {

	/*
	@GetMapping("/thymeleaf/example")
	public String thymeleafExample( Model model ) {
		
		Person examplePerson = new Person();
		examplePerson.setId(1L);
		examplePerson.setName("홍길동");
		examplePerson.setAge(11);
		examplePerson.setHobbies( List.of("운동", "독서") );
		
		model.addAttribute( "person", examplePerson );
		model.addAttribute( "today", LocalDate.now() );
		
		return "example";
		
	}
	*/
	
	@GetMapping("/thymeleaf/example")
	public ModelAndView thymeleafExample() {
		
		Person examplePerson = new Person();
		examplePerson.setId(1L);
		examplePerson.setName("손흥민");
		examplePerson.setAge(31);
		examplePerson.setHobbies( List.of("축구", "수영", "드라이빙") );
		
		ModelAndView mv = new ModelAndView();
		mv.addObject( "person", examplePerson );
		mv.addObject( "today", LocalDateTime.now() );
		mv.setViewName( "example" );
		
		return mv;
		
	}
	
}
