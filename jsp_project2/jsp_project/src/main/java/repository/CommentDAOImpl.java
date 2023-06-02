package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private static Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	
	@Override
	public int insert(CommentVO cvo) {
		log.info("insert DAO 진입~!");
		isOk = sql.insert(NS+"insert",cvo);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}


	@Override
	public List<CommentVO> list(int bno) {
		log.info("list DAO 진입~!");
		return sql.selectList(NS+"list",bno);
	}


	@Override
	public int remove(int cno) {
		log.info("remove DAO 진입~!");
		isOk = sql.delete(NS+"delete",cno);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}


	@Override
	public int modify(CommentVO cvo) {
		log.info("update DAO 진입~!");
		isOk = sql.update(NS+"update",cvo);
		if(isOk > 0 ) {
			sql.commit();
		}
		return isOk;
	}

}
