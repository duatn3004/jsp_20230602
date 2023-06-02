package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	private SqlSession sql;
	private String NS = "BoardMapper.";
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(BoardVO bvo) {
		log.info("insert DAO 진입!");
		int isOk = sql.insert(NS+"insert",bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> list() {
		log.info("list DAO 진입!");
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = sql.selectList(NS+"list");
		return list;
	}

	@Override
	public BoardVO detail(int bno) {
		log.info("detail DAO 진입!");
		//조회수
		int isOk = sql.update(NS+"cnt",bno);
		if(isOk > 0) {
			sql.commit();
		}
		return sql.selectOne(NS+"selectOne",bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("modify DAO 진입!");
		int isOk = sql.update(NS+"update",bvo);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("delete DAO 진입!");
		return sql.delete(NS+"delete",bno);
	}

	@Override
	public int totCount(PagingVO pgvo) {
		log.info("totCount DAO 진입!");
		return sql.selectOne(NS+"tot",pgvo);
	}

	@Override
	public List<BoardVO> pageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		log.info("pageList DAO 진입!");
		return sql.selectList(NS+"selectList",pgvo);
	}

}
