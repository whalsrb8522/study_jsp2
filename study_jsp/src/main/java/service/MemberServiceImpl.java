package service;

import java.util.List;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}
	
	@Override
	public int register(MemberVO mvo) {
		return mdao.insert(mvo);
	}

	@Override
	public List<MemberVO> list() {
		return mdao.list();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		return mdao.selectOne(mvo);
	}

	@Override
	public int lastlogin(String id) {
		return mdao.updateLastLogin(id);
	}

	@Override
	public MemberVO detail(MemberVO mvo) {
		return mdao.selectDetail(mvo);
	}

	@Override
	public int modify(MemberVO mvo) {
		return mdao.updateOne(mvo);
	}

	@Override
	public int delete(String id) {
		return mdao.deleteOne(id);
	}
}
