package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {
	int register(BoardVO bvo);
	List<BoardVO> list();
	BoardVO detail(int bno);
	int modify(BoardVO bvo);
}
