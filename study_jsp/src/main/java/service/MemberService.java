package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {
	int register(MemberVO mvo);
	List<MemberVO> list();
}
