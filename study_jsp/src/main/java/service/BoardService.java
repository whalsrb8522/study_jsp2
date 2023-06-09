package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {
	int register(BoardVO bvo);
	List<BoardVO> list();
	BoardVO detail(int bno);
	int modify(BoardVO bvo);
	int delete(int bno);
	int getTotal(PagingVO pgvo);
	List<BoardVO> getPageList(PagingVO pgvo);
	String getFileName(int bno);
}
