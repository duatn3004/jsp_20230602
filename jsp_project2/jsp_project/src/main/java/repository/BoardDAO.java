package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> list();

	BoardVO detail(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int totCount(PagingVO pgvo);

	List<BoardVO> pageList(PagingVO pgvo);

}