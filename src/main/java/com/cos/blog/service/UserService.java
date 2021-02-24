package com.cos.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IOC를 해준다
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();  //1234 원문
		String encPassword = encoder.encode(rawPassword); //해쉬화
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	/*
	 * @Transactional(readOnly = true)//select 할떄 트랜젝션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
	 * public User login(User user) { return
	 * userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword(
	 * )); }
	 */
}
