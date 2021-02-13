package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//->@Controller

//사용자가 요청 -> 응답(Data)
//->@RestController
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpController Test: ";
	
	//http://localhost:8000/blog/http/lombok (select)
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : " + m.getId());
		m.setUsername("cos");
		System.out.println(TAG+"getter : " + m.getId());
		return "lombok test 완료";
	}
	//http://localhost:8000/blog/get (select)
	//인터넷 브라우저 요청은 only get만 가능하다. -> post,put,delete 테스트 원하면 postman이용
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 : " + m.getId() + " , " + m.getUsername() + " , " + m.getPassword();
	}
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody  Member m) {//MessageConverter(스프링부트)
		return "post 요청 : " + m.getId() + " , " + m.getUsername() + " , " + m.getPassword() + " , " + m.getEmail();
	}
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m.getId() + " , " + m.getUsername() + " , " + m.getPassword() + " , " + m.getEmail();
	}
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	
}
