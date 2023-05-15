package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {
	int insert(MemberVO mvo);
	List<MemberVO> list();
	MemberVO selectOne(MemberVO mvo);
	int updateLastLogin(String id);
	int updateOne(MemberVO mvo);
	MemberVO selectDetail(MemberVO mvo);
	int deleteOne(String id);
}
