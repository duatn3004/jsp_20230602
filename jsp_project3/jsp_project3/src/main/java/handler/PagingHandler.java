package handler;

import domain.PagingVO;

public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount; // 총게시물수 
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		// 나머지 값 계산
		// 페이지 번호 / 한 화면의 게시글 수 *한 화면의 게시글 수
		// *1.0의 이유 -> 정수 / 정수 => 정수 (소수점을 남기기 위해서)
		//(1/10) * 10 => 0.1(1) * 10 => 10
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (pgvo.getQty() *1.0))*pgvo.getQty();
		this.startPage = this.endPage - 9;
		// 페이지네이션의 전체 끝 페이지
		int realEndPage = (int)Math.ceil((totalCount*1.0) / pgvo.getQty());
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		// 현재화면에서 보여지는 startpage = 1, 11, 21
		this.prev = this.startPage > 1; // 존재여부
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
