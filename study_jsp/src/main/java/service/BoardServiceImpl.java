package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;
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
		return bdao.update(bvo);
	}

	@Override
	public int delete(int bno) {
		return bdao.delete(bno);
	}

	@Override
	public int getTotal() {
		return bdao.totCount();
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		return bdao.pageList(pgvo);
	}
}
