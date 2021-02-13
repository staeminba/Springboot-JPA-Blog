package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Getter Setter
@NoArgsConstructor //Bean 생서아
@AllArgsConstructor //전체 생성자
@Builder //빌더 패턴
@Entity //테이블 생성
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne //여러개의 답변은 하나의 게시글에 존재함 OneToOne -> 하나의 글에 하나의 답변 , OneToMany->하나의 답글에 여러글의 개시글
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne //여러개의 답변을 하나의 유저가 작성 가능 
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private Timestamp createDate;
}
