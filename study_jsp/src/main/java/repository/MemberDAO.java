package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO mvo);
	List<MemberVO> list();
}
