package service;

import java.util.List;

import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public int register(BoardVO bvo) {
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> list() {
		return bdao.selectList();
	}

	@Override
	public BoardVO detail(int bno) {
		return bdao.selectDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		return bdao.upddate(bvo);
	}
}
