package handler;

import domain.PagingVO;

public class PagingHandler {
	private int startPage; //현재 화면에서 보여줄 시작 페이지네이션 번호 
	private int endPage; //현재 화면에서 보여줄 끝 페이지네이션 번호
	private boolean prev, next; //이전, 다음 버튼 존재 여부
	
	private int totalCount;  //총 게시물 수
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		//나머지 값 계산
		//127개의 게시글 페이지네이션 번호
		// 1~13 까지 필요
		//현재페이지에 보이는 startpage=1, endpage=10 다음
		//11페이지에 보이는 이전 startpage=11, endpage=13
		//페이지 번호 / 한 화면의 게시글 수 * 한 화면의 게시글 수
		// *1.0의 이유 -> 정수 / 정수 => 정수 (소수점을 남기기 위해서)
		// (21 / 10) *10 => 2.1(3) *10 => 30
		// (2 / 10) * 10 => 0.5(1) * 10 => 10, 20, 30...
		// 1~0페이지까지 endPage = 10
		// 11~13페이지까지 endPage = 13
		this.endPage = (int) Math.ceil(pgvo.getPageNo() / (pgvo.getQty() * 1.0)) * pgvo.getQty();
		this.startPage = this.endPage - 9;
		
		//페이지네이션의 전체 끝 페이지
		int realEndPage = (int)(Math.ceil((totalCount * 1.0) / pgvo.getQty()));  //13page
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		
		//현재화면에서 보여지는 startpage =1, 11, 21
		this.prev = this.startPage > 1;  //존재여부 
		this.next = this.endPage < realEndPage;  
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public PagingVO getPgvo() {
		return pgvo;
	}

	public void setPgvo(PagingVO pgvo) {
		this.pgvo = pgvo;
	}
}
