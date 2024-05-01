package com.green.entity;

import com.green.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity							// 클래스를 테이블로 생성한다
@Data							// @Getter + @Setter + @toString + hashCode, equals
@NoArgsConstructor				// 기본생성자
@AllArgsConstructor				// 모든인자 생성자
@SequenceGenerator(
		name = "COMMENTS_SEQ_GENERATOR",
		sequenceName = "COMMENTS_SEQ",
		initialValue = 1,		// 초기값
		allocationSize = 1		// 증가치
		)
public class Comments {

	@Id							// 기본키
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "COMMENTS_SEQ_GENERATOR"
	)
	private Long id;			// id
	
	// private  article_id;
	@ManyToOne					// 관계 : 다대일 설정 (Comments ↔ Article)
	@JoinColumn(name="article_id")	// 외래키 칼럼 (부모키 Article id 칼럼)(외래키 이름이 아님)
	private Article article; 	// 연결될 entity 객체의 이름
	
	// @Column(name="nick_name", nullable=false, length=255)	// nullable=false → null 사용할 수 있는가 : false
	// oracle 11g varchar2 최대 4000 byte
	// oracle 12c varchar2 최대 32000 byte
	@Column
	private String nickname;	// nickname
	
	@Column
	private String body;		// body(댓글내용)

	//-------------------------------------------------------------------------
	
	//public static Comments createComment(CommentDto dto, Long articleId) {	// Article article 추가 필요, article 안에 Long articleId 有
	public static Comments createComment(CommentDto dto, Article article) {		// CommentService 의 createComment 인자도 수정
		
		if( dto.getId() != null )	// 입력된 댓글에 id 가 존재하면
			throw new IllegalArgumentException(
					"댓글 생성 실패! 댓글의 id 가 없어야합니다"
					);

		if( dto.getArticleId() != article.getId() )
			throw new IllegalArgumentException(
					"댓글 생성 실패! 게시글의 id 가 잘못되었습니다"
					);
		// dto.getArticleId() : 넘어온(= 입력받은) 게시글의 id
		// article.getId()    : createComment 부른놈 = Service : 조회한 게시글의 id

		
		return new Comments(
				dto.getId(),		// 입력받은, 댓글 아이디
				article,			// 조회한, 부모 게시글 정보
				dto.getNickname(),	// 입력받은, 댓글 닉네임
				dto.getBody()		// 입력받은, 댓글 내용
				);
	}
	
}
