package com.green.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.CommentDto;
import com.green.entity.Article;
import com.green.entity.Comments;
import com.green.repository.ArticleRepository;
import com.green.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ArticleRepository articleRepository;

	
	// 1. 댓글 조회
	public List<CommentDto> comments(Long articleId) {
	
		/*
		// 1. 댓글을 DB 에서 조회 → Entity 에 담는다
		// 댓글 DB 에서 조회 시 articleId 로 조회함
		// Comments = Entity table → db 와 같은 구조
		List<Comments> commentList = commentRepository.findByArticleId(articleId);
		
		// 2. Entity 에 담겨있는 조회된 댓글 → Dto 에 담음 : Dto 로 변환
		// List<CommentDto> dtos = commentList
		// dtos : CommentDto 타입 ↔ commentList : Comments 타입 → 타입이 달라서 바로 못담음 → new ArrayList<>() 작업 필요
		List<CommentDto> dtos = new ArrayList<CommentDto>();
		for (int i = 0; i < commentList.size(); i++) {
			Comments c = commentList.get(i);
			CommentDto dto = CommentDto.createCommentDto(c);
			dtos.add(dto);
		}
		
		// 3. 결과를 반환
		
		return dtos;
		*/
		
		// 위 1~3번 한줄로 줄이는 문법
		/* ArrayList.stream()
		 * .map( (comment) -> {
		 *    CommentDto.createCommentDto( comment )
		 *  } )
		 *  -----------------------------------------------------------------
		 *  집합.map() : stream 전용함수
		 *  집합(Collection) 에 element 들을 반복으로 조작
		 *  .map()  vs  .forEach() - 둘다 배열을 모두 조작한다
		 *  -----------------------------------------------------------------
		 *  .map()  :  내부의 element 값이나 사이즈 변경할 때 사용 : ex. 모두 대문자로 변경
		 *  .forEach() : 내부의 element 값이나 사이즈 변경되지 않을 때 사용
		 */
		return commentRepository.findByArticleId( articleId )	
				.stream()	// stream 으로 전환
				.map( comment -> CommentDto.createCommentDto( comment ) )		// map 으로 전환
				.collect( Collectors.toList() );
		// ArrayList 같은 배열을 한번에 처리하기 위한 문법
		// 문법이 독특하고 생소해서 아직까지 잘 안쓰이고 있지만, C# 에서 stream 문법을 먼저 도입했고 자바 1.8 버전에서 도입함
		
		// .map( comment -> CommentDto.createCommentDto( comment ) )  :  Comments = Entity → Dto 전환
		// .collect( Collectors.toList() );  :  ArrayList 전환
	}


	@Transactional		// 오류 발생 시 DB 롤백하기 위해 (throw 사용하는 이유)
	public CommentDto create(Long articleId, CommentDto dto) {
		
		// 1. 게시글 조회 및 조회실패 예외 발생 처리
		// 예외 : 게시글에 존재하지 않는 articleId 가 넘어오면 조회결과가 없다 -> Throw
		// ex. Article 테이블에 id 1~6 까지인데, comment 의 article_id 가 7 이 넘어올순 없다
		Article article = articleRepository.findById(articleId)
				.orElseThrow( () -> new IllegalArgumentException("댓글 생성 실패! 대상 게시물이 없습니다") );
		// 조회(.findById(articleId))와 예외처리(.orElseThrow()) 동시 실시
		//-------------------------------------------------------------------------------------------------------------
		// 2. (조회되었다면) 댓글 Entity 생성 → 저장할 데이터(comments)를 만든다
		//Comments comments = Comments.createComment( dto, articleId );
		Comments comments = Comments.createComment( dto, article );
		// dto : CommentDto 데이터 넘겨받음
		// Article article : 게시글 ↔ Comments : 댓글		
		//-------------------------------------------------------------------------------------------------------------
		// 3. 댓글 Entity 를 DB 에 저장
		Comments created = commentRepository.save(comments);
		//-------------------------------------------------------------------------------------------------------------
		// 4. 저장된 Comments(Entity 타입의 data) type 의 created → dto 로 변환 후 Controller 로 return
		return CommentDto.createCommentDto( created );
		// 변환 이유가 controller 에서 entity type 을 사용하지 않기 위해
	}	
	
	
}
