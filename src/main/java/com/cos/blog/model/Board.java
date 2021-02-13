package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
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
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable=false , length = 100)
	private String title;
	
	@Lob //대용량 데이터 사용시
	private String content; //섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨
	
	@ColumnDefault("0") //int 므로 ' '필요 없음 
	private int count;//조회수
	
	//private int userId; //DB는 오브제그트를 저장 못하므로  FK 사용,
	@ManyToOne(fetch = FetchType.EAGER)//Many = Board , One = User  한명의 user는 여러개의 게시글 작성 가능
	//fetch = FetchType.EAGER  ManyToOne 기본전략, 안적어도됨 -> 무조건 가져온다
	@JoinColumn(name="userId")
	private User user; //자바는 오브젝트를 저장할수 있으므로 orm에선 Object 사용 
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //mappedBy 연관관계의 주인이 아니다. (FK  가 아니다) DB에 컬럼을 만들지 말라
	/*fetch = FetchType.LAZY OneToMany 기본전략, 안적어도됨 -> 필요하면 가져오고 안필요하면 안가져옴
	댓글이 보이는 블로그를 만들것이기 때문에 EAGER로 변경. 펼치기경우 LAZY로 가져와도됨 
	*/
	//@JoinColumn(name="replyId") 필요가 없다
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
}
