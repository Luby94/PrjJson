package com.green.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usersjpa")
  // @Table : entity 는 table 명과 동일하지만, oracle 은 user table 명이 불가함
  // table 명을 변경해야 한다 (usersjpa)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
  // UserDetails 인터페이스를 구현하여 User 엔티티를 생성한다
public class User implements UserDetails {
	// User → F2 : Add ~
	
	// 칼럼 부분을 표시
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// db 정책
	@Column(name="id", updatable = false)	// updatable = false → 수정할 수 없음
	private Long id;
	
	@Column(name="email", nullable = false, unique = true)	// unique = true → 중복방지
	private String email;

	@Column(name="password", nullable = false)
	private String password;
	
	//-----------------------------------------------------------------

	// 생성자 대신 아래 문법으로 사용 가능
	@Builder
	  // @Builder : 
	  // User user = User.builder()
	  //                 .email()
	  //                 .password()
	  //                 .build();
	// @Builder 장점 : 코딩 자유도가 생김
	// Parameter 순서 상관없음, 생성자 편하게 갖다씀( .password("aaa").email("aaa@abc.com") )
	  // 생성자 : 꼭 그자리에 해당 변수 있어야 함
	// Getter 만 있고 Setter 는 없음 → 생성자로만 값 생성 가능
	public User(String email, String password) {	// id 는 수정할 수 없기때문에 빼고 생성한다
		super();
		this.email = email;
		this.password = password;
	}
	
	//-----------------------------------------------------------------
	
	// implements 할 메소드
	@Override	// 권한 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자가 가지고 있는 권한의 목록을 반환, 현재는 User 권한만 반환
		return List.of( new SimpleGrantedAuthority("user") );
	}

	@Override	// 사용자 비밀번호
	public String getPassword() {
		
		return password;
	}

	@Override	// 사용자 아이디(이메일)
	public String getUsername() {	// <input name="username" >
		
		return email;
	}

	@Override	// 계정 만료여부 반환
	public boolean isAccountNonExpired() {
		// 계정이 만료되었는지를 확인하는 로직
		return true;	// false → true 변경 / true : 만료되지 않음
	}

	@Override	// 계정 잠금 여부 반환
	public boolean isAccountNonLocked() {
		// 계정 잠금 여부를 확인하는 로직
		return true;	// false → true 변경 / true : 잠금되지 않음
	}

	@Override	// 패스워드 만료여부 반환
	public boolean isCredentialsNonExpired() {
		// 패스워드가 만료되었는지 확인하는 로직
		return true;	// false → true 변경 / true : 만료되지 않음
	}

	@Override	// 계정 사용 가능 여부 반환
	public boolean isEnabled() {
		// 계정 사용 가능 여부 반환하는 로직
		return true;	// true : 사용가능
	}

	

	
	
}
