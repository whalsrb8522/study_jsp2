package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {
	int insertComment(CommentVO cvo);
	List<CommentVO> selectComment(int bno);
}
