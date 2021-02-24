package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행학 완료가 되면 userDetails 타입의 오브젝트를
//스프링 시큐리티의 고유한 세션저장소에 저장.
@Getter
public class PrincipalDetail implements UserDetails{
	private User user; //콤포지션(안에 품고 있는거)


	 public PrincipalDetail(User user) {
		// TODO Auto-generated constructor stub
		this.user = user;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴(true : 만료 안됨)
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 잠기지 않았는지 리턴(true : 안잠김)
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//비밀번호 만료되지 않았는지 리턴(true : 만료 안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//계정이 활성화 되었는지 리턴(true : 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	//계정의 권한 리턴(true : 만료 안됨)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		//권한 여러개면 for문
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		/*collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				return "ROLE_"+user.getRole(); // ROLE_USER ROLE_ SPRING 에서 붙여줘야함
			}
		});*/
		collectors.add(()->{return "ROLE_"+user.getRole();}); // ROLE_USER ROLE_ SPRING 에서 붙여줘야함
		
		return collectors;
	}
}
