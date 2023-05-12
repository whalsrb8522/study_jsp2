package domain;

public class MemberVO {
//	id varchar(20) NOT NULL PRIMARY KEY,
//	password varchar(20) NOT NULL,
//	name varchar(20) NOT NULL,
//	email varchar(50),
//	phone int,
//	regdate datetime NOT NULL DEFAULT NOW(),
//	lastlogin datetime
	
	private String id;
	private String password;
	private String name;
	private String email;
	private int phone;
	private String regdate;
	private String lastlogin;
	
	// 기본 생성자
	public MemberVO() {}
	
	// 로그인
	public MemberVO(String id, String password) {
		this.id = id;
		this.password = password;
	}
	
	// 회원 가입
	public MemberVO(String id, String password, String name, String email, int phone) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	// 회원 리스트
	public MemberVO(String id, String name, String regdate) {
		this.id = id;
		this.name = name;
		this.regdate = regdate;
	}	
	
	// 회원 상세
	public MemberVO(String id, String name, String email, int phone, String regdate, String lastlogin) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.regdate = regdate;
		this.lastlogin = lastlogin;
	}

	// 회원 수정
	public MemberVO(String id, String password, String name, String email, int phone, String regdate) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	
	
	// getter
	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getPhone() {
		return phone;
	}

	public String getRegdate() {
		return regdate;
	}

	public String getLastlogin() {
		return lastlogin;
	}

	// setter
	public void setId(String id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public void setLastlogin(String lastlogin) {
		this.lastlogin = lastlogin;
	}
}
