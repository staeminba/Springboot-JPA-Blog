package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;

//빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것 아래 3개 세트개념
@Configuration //빈등록
@EnableWebSecurity //시큐리티 필터 가 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	@Bean //IoC가 됨
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인해주는데 password를 가로채기 하는데
	//해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	//같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("@22222222222");
		System.out.println(auth.toString());
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰비활성화(테스트시 걸어두는게 좋다)
			.authorizeRequests() // request 가 들어오면
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") //auth로 들어오는건
				.permitAll() //누구나 들어올수 있다
				.anyRequest() //다른 요청은
				.authenticated() //인증이 되야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/loginProc")//스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/");
				//.failureUrl("/fail")
		
	}
}
