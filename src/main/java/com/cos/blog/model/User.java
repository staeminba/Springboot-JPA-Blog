package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter Setter
@NoArgsConstructor //Bean 생서아
@AllArgsConstructor //전체 생성자
@Builder //빌더 패턴
//ORM -> Object -> 테이블로 매핑해주는 기술
@Entity//User 클래스가 MySQL에 테이블이 생성됨.
public class User {
	private enum RoleType {ADMIN,  USER, MANAGER}
	@Id //Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 ,시퀀스(oracle),auto_increment(my_sql)
	private int id; 
	
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 100) //123456 => 해쉬 (비밀번호 암호화)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'USER'")
	//private String role;
	
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum 을 쓰는게 좋다. //admin , user, manager (maganer 같은 오타가 들어올수 있음) 
	
	@CreationTimestamp //시간이 자동입력
	private Timestamp createDate;
	
}
