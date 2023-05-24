package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {
	int insert(BoardVO bvo);
	List<BoardVO> selectList();
	BoardVO selectDetail(int bno);
	int update(BoardVO bvo);
	int delete(int bno);
	int totCount();
	List<BoardVO> pageList(PagingVO pgvo);
}
