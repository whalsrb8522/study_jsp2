package domain;

public class PagingVO {
	private int pageNo;  //현재 화면에 출력되는 페이지네이션 번호
	private int qty; //한 페이지에 보여줄 게시글 수  => 10개
	
	// 검색 : 멤버변수 추가
	private String type;
	private String keyword;
	
	public PagingVO() {  //페이지네이션을 클릭하기전 기본 값으로 지정
		this(1, 10);
	}
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageStart() {
		return (this.pageNo-1)*10;  //시작 limit 번지 구하기
	}
	
	// 검색 : 타입을 배열 처리
	public String[] getTypeToArray() {
//		return this.type == null ? new String[] {} : this.type.split("");
		return this.type.split("");
	}
	
	// Getter
	public int getPageNo() {
		return pageNo;
	}
	public int getQty() {
		return qty;
	}
	public String getType() {
		return type;
	}
	public String getKeyword() {
		return keyword;
	}
	
	// Setter
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
}
