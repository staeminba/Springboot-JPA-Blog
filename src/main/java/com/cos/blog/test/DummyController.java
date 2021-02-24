package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {

	@Autowired //의존성 주입(DI)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 존재하지 않습니다.";
		}
		return "삭제되었습니다. ID : " + id;
		
	}
	
	//save 함수는 id를 전달하지 않으면 insert를 하고 id를 전달하면 해당 id에 대한 데이터가 있으면 update
	//해당 id에 대한 데이터가 없으면 insert
	//email , password 만 수정
	//@RequestBody = json으로 받을때 씀
	@Transactional //함수 종료시에 자동 commit이 됨.
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) throws IllegalAccessException {//json 데이터를 요청 => Java Objerct로 변환(Message Converter 의 Jackson 라이브러리가 변환)
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalAccessException("수정에 실패하였습니다.");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user); 
		
		//더티 체킹
		
		return user;
	}
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		/*
		 * if(pagingUser.isFirst()) {
		 * 
		 * }
		 */
		List<User> users = pagingUser.getContent();
		return users;
	}
	
	//{id} 주소로 파라메터를 전달 받으 수 있음
	//http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) throws IllegalAccessException {
		//user/4를 찾으면 데이터 베이스에서 못찾으면 user가 null이 되므로 retrun null이 되어 문제됨
		//Optional로 너의 User객체를 감싸서 가져올테니 null인지 판단해서 return 해줌
		/*
		 * User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
		 * 
		 * @Override public User get() { // TODO Auto-generated method stub return new
		 * User(); } });
		 */
		
		//람다식 java 1.8
		/*
		 * User user = userRepository.findById(id).orElseThrow(()->{ return new
		 * IllegalAccessException("해당 유저는 없습니다. id : " + id); });
		 */
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalAccessException>() {
			@Override
			public IllegalAccessException get() {
				// TODO Auto-generated method stub
				return new IllegalAccessException("해당 유저는 없습니다. id : " + id);
			}
		});
		//요청 : 웹브라우저
		//user 객체 = 자바 오브젝트
		//변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
		//스프링부트 = MessageConverrior라는 애가 응답시에 자동 작동
		//자바 오브텍트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출
		//user 오브젝트를 json으로 변환해서 브라우저에 전달
		return user;
	}
	@PostMapping("/dummy/join")
	public String join(User user) {
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
