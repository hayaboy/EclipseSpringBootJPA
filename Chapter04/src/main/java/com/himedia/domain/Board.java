package com.himedia.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;


@Entity  //만약 Board 클래스와 다른 테이블 이름을 매핑하려면 반드시 @Table을 사용
//@Table(name = "E_BOARD", uniqueConstraints = {@UniqueConstraint(columnNames = {"SEQ","WRITER"})}) uniqueConstraints 여러개의 칼럼이 결합되어 유일성이 보장해야 하는 경우
@TableGenerator(name = "BOARD_SEQ_GENERATOR", table = "ALL_SEQUENCES", pkColumnValue = "BOARD_SEQ", initialValue = 0, allocationSize = 1) //ALL_SEQUENCES라는 키 생성 테이블을 만들고, BOARD_SEQ 이름으로 증가되는 값을 저장하라는 의미, allocationSize를 1로 지정했기 때문에 값을 한번 꺼내 쓸때마다 자동으로 1씩 증가 
public class Board {
	
	
	@Id             //strategy PK 값 생성전략 지정 TABLE, SEQUENCE, IDENTITY, AUTO (TABLE:하이버네이트가 테이블을 사용하여 PK값을 생성 SEQUENCE:시퀀스를 이용하여 PK값 생성) 
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR") //strategy 자동 생성 전략, generator 이미 생성된 키 생성기를 참조
	private Long seq;

	//@Column(insertable = true, length = 34, precision = 4, scale = 2) //INSERT 생성할 때 이 칼럼을 포함할 것인지 결정(기본값 true), updatable도 동일
	// length 문자열 타입의 칼럼 길이(기본값 255),  precision 숫자 타입의 전체 자릿수 지정, scale 숫자 타입의 소수점 자릿수 지정
	private String title;
	private String writer;
	private String content;
	@Temporal(TemporalType.TIME)  //java.util.Date 타입의 날짜 데이터를 매핑할 때 사용
	private Date createDate;
	private Long cnt;
	
	
	//@Transient   엔티티 클래스 내의 특정 변수를 영속 필드에서 제외
	//private String SeachKeyword;
	
	public Long getSeq() {
		return seq;
	}
	public void setSeq(Long seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Long getCnt() {
		return cnt;
	}
	public void setCnt(Long cnt) {
		this.cnt = cnt;
	}
	
	@Override
	public String toString() {
		return "Board [seq=" + seq + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", createDate=" + createDate + ", cnt=" + cnt + "]";
	}
}
