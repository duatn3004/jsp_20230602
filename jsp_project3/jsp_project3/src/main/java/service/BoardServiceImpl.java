package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	private BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}
	
	@Override
	public int insert(BoardVO bvo) {
		log.info("insert service 진입!");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> list() {
		log.info("list service 진입!");
		return bdao.list();
	}

	@Override
	public BoardVO detail(int bno) {
		log.info("detail service 진입!");
		return bdao.detail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		log.info("modify service 진입!");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("remove service 진입!");
		return bdao.delete(bno);
	}

	@Override
	public int getTotal(PagingVO pgvo) {
		log.info("페이징 진입!");
		return bdao.totCount(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		// TODO Auto-generated method stub
		return bdao.pageList(pgvo);
	}

}
