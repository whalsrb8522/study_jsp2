package domain;

public class BoardVO {
	private int bno;
	private String title;
	private String writer;
	private String regdate;
	private String content;
	private String image;
	private int readcount;
	
	public BoardVO() {}
	
	// 리스트 출력
	public BoardVO(int bno, String title, String writer, String regdate, int readcount) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.readcount = readcount;
	}
	
	// 글쓰기
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	// 상세 페이지
	public BoardVO(int bno, String title, String writer, String regdate, String content, int readcount) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
		this.readcount = readcount;
	}
	
	// 글수정
	public BoardVO(int bno, String title, String content) {
		this.bno = bno;
		this.title = title;
		this.content = content;
	}

	public BoardVO(int bno, String title, String writer, String regdate, String content, String image, int readcount) {
		this.bno = bno;
		this.title = title;
		this.writer = writer;
		this.regdate = regdate;
		this.content = content;
		this.image = image;
		this.readcount = readcount;
	}

	// getter
	public int getBno() {
		return bno;
	}
	public String getTitle() {
		return title;
	}
	public String getWriter() {
		return writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public String getContent() {
		return content;
	}
	public int getReadcount() {
		return readcount;
	}
	public String getImage() {
		return image;
	}
	
	// setter
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
