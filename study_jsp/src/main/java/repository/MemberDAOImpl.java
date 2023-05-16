package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
//	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);

	private SqlSession sql;
	
	// NameSpace = SQL이 mapper를 구분하기 위한 이름
	// NameSpace.SQL구문이름
	private String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(MemberVO mvo) {
		// sql.insert(NS + 이름, 객체);
		int isOk = sql.insert(NS + "reg", mvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<MemberVO> list() {
		return sql.selectList(NS + "list");
	}

	@Override
	public MemberVO selectOne(MemberVO mvo) {
		return sql.selectOne(NS + "login", mvo);
	}

	@Override
	public int updateLastLogin(String id) {
		int isOk = sql.update(NS + "lastlogin", id);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int updateOne(MemberVO mvo) {
		int isOk = sql.update(NS + "modify", mvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO selectDetail(MemberVO mvo) {
		return sql.selectOne(NS + "detail", mvo);
	}

	@Override
	public int deleteOne(String id) {
		int isOk = sql.delete(NS + "delete", id);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
}
