package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {
	int register(MemberVO mvo);
	List<MemberVO> list();
	MemberVO login(MemberVO mvo);
	int lastlogin(String id);
	MemberVO detail(MemberVO mvo);
	int modify(MemberVO mvo);
	int delete(String id);
}
