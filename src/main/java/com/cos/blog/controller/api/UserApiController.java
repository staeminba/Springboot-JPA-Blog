package com.cos.blog.controller.api;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	

	/*
	 * @Autowired private HttpSession session;
	 */
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user) {
			System.out.println("UserApli");
			
			
			userService.join(user);
			
			return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	/*
	 * @PostMapping("/api/user/login") public ResponseDto<Integer>
	 * login(@RequestBody User user,HttpSession session;){ System.out.println("Enter login"); User
	 * principal = userService.login(user); //pricipal(접근주체) if(principal != null) {
	 * session.setAttribute("principal", principal); } return new
	 * ResponseDto<Integer>(HttpStatus.OK.value(),1); }
	 */
}
