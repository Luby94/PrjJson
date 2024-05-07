package com.green.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.dto.UserDto;
//import com.green.dto.AddUserRequest;
import com.green.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {
	
	// 실행 → filterChain : .authorizeHttpRequests()
	//        → .requestMatchers("/login", "/signup", "/user").permitAll()
	//          로그인체크 → 로그인 안되어있으면 /login
	//        → 회원가입(/signup) : /login 이 다시 회원가입으로 보낸 것
	//        → /user(db 저장) → /login
	//        → 아이디/암호 → 체크 → 로그인 되어있으면(성공하면) .loginSuccessUri("/") 로 이동
	//        → 아이디/암호 → 체크 → 로그인 실패 → /login 로 이동

    private final UserService userService;

    // 회원가입 로직 ← form tag 값을 저장 (Repository 로 안가고 UserService 로 감)
    @PostMapping("/user")
    public String signup(UserDto request) {
        userService.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}