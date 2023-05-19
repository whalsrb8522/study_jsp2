package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession sql;
	private String NS = "BoardMapper.";
	
	int isOk;
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(BoardVO bvo) {
		isOk = sql.insert(NS + "insert", bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		return sql.selectList(NS + "list");
	}

	@Override
	public BoardVO selectDetail(int bno) {
		isOk = sql.update(NS + "readCountUp", bno);
		if (isOk > 0) {
			sql.commit();
			return sql.selectOne(NS + "detail", bno);
		}
		return null;
	}

	@Override
	public int update(BoardVO bvo) {
		isOk = sql.update(NS + "update", bvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		isOk = sql.delete(NS + "delete", bno);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
}
