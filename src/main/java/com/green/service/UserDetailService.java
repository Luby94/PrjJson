package com.green.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.green.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// not null 인 애들, 필수입력 항목의 생성자
@Service
// 반드시 UserDetailsService interface 를 implements 해야함
public class UserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	// UserDetailService → F2 : add unimplement method
	// 로그인에 필요한 Username 로그인 아이디(email) 를 통해서
	//   UserDetail 정보를 가지고 온다
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {	// username → email 수정
		// 
		
		UserDetails userDetails = userRepository.findByEmail(email)
				.orElseThrow( () -> new IllegalArgumentException(email) );
		
		return userDetails;
	}

	
	
}
