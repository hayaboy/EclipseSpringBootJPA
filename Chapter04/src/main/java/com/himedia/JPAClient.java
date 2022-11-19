package com.himedia;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.himedia.domain.Board;

public class JPAClient {

	public static void main(String[] args) {
		// JPA를 이용하기 위해서 EntityManager 생성
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("Chapter04");
		EntityManager em=emf.createEntityManager();
		
		// Transaction 생성  
				EntityTransaction tx = em.getTransaction();
				
				//Transaction 시작
				tx.begin();
				
				try {
					Board board=new Board();
					board.setTitle("오늘 토요일3");
					board.setWriter("관리자");
					board.setContent("장날이네요3");
					board.setCreateDate(new Date());
					board.setCnt(0L);
//					
					//수정할 게시글 조회
//					Board board=em.find(Board.class, 1L);
//					board.setTitle("검색한 게시글의 제목 수정");
					
					//삭제할 게시글 삭제
//					Board board1=em.find(Board.class, 1L);
//					board1.setSeq(1L);

					//게시글 삭제, 삭제시 주의할 점은 삭제하기 전에 반드시 삭제할 엔티티를 검색해서 영속성 컨텍스트에 등록
//					board1.setSeq(1L);
//					em.remove(board1);
					
					
					em.persist(board); //Board 엔티티에 설정된 값을 BOARD 테이블에 저장함(INSERT)
					
					// 아래코드가 생략되면 Board 테이블에 저장되지 않는다.
					//왜냐하면 JPA가 실제 테이블에 등록/수정/삭제 작업을 처리하기 위해서는 해당 작업이 반드시 트랜잭션 안에서 수행되어야 함
					//만약 트랜잭션을 시작하지 않았거나 등록/수정/삭제 작업 이후에 트랜잭션을 종료하지 않으면 요청한 작업이 실제 데이터베이스에 반영되지 않음
					
					

					
					
					//글 상세 조회
//					Board serarchBoard=em.find(Board.class, 1L);
//					System.out.println(serarchBoard.toString());
					
					//글 목록 조회, 테이블에 저장된 특정 데이터를 상세 조회하려면 find(), 목록조회시 JPA에서 제공하는 별도의 쿼리
					String jpql="select b from Board b order by b.seq desc";
					List<Board> boardList=em.createQuery(jpql, Board.class).getResultList();
					
					for(Board brd: boardList) {
						System.out.println(brd.toString());
					}
					tx.commit();
					
					
					
				}catch(Exception e) {
					//Transaction rollback
					tx.rollback();
				}finally {
					em.close();
					emf.close();
				}	
				
	}
	
}
