package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentDto;
import com.green.entity.Comments;
import com.green.service.CommentService;

// 댓글 조회, 댓글 추가, 수정, 삭제
@RestController
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;

	// 1. 댓글 조회(GET)
	@GetMapping("/api/articles/{articleId}/comments")
	public ResponseEntity< List<CommentDto> > comments( @PathVariable Long articleId ) {
		
		// List<Comments> : data 만 리턴
		// ResponseEntity<List<Comments>> : 코드도 리턴
		// {articleId} : 조회할 파라미터 → comments() 안에 기입
		
		// 정보조회를 서비스에게 위임
		List<CommentDto> dtos = commentService.comments( articleId );	// CommentService 의 return : dtos
		
		// @ResponseBody : status.ok + dtos(ArrayList → 나중에 json 으로 출력) 를 리턴
		return ResponseEntity.status(HttpStatus.OK).body( dtos );
		// .body( dtos ) : json 으로 출력 → public 부분의 리턴 타입인 List<CommentDto> 를 RestAPI 로 출력시 json 으로 출력
		// @RestController 이기 때문
		
	}
	
	
	// 2. 댓글 생성(POST)
	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<CommentDto> create( 
			@PathVariable Long articleId,	// {articleId} : 게시글 번호(ex. 3번 게시글의 댓글)
			@RequestBody CommentDto dto		// 입력된 자료들 input, select
			) {
	
		// CommentDto 와 게시글 번호가 넘어옴
		// 자료받아와서 Service 한테 저장해라고 시킴
		// → 넘어온 값을 서비스에 넘김
		CommentDto createdDto = commentService.create( articleId, dto );
		
		return ResponseEntity.status(HttpStatus.OK).body( dto );
		
	}
	
	
	/*
	// 3. 댓글 수정(PATCH)
	@PatchMapping("/api/comments/{id}")
	
	// 4. 댓글 삭제(DELETE)
	@DeleteMapping("/api/comments/{id}")
	*/
}
