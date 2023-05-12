package repository;

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
		int isOk = sql.insert(NS+"reg", mvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
}
