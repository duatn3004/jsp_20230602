package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO cvo);

	List<CommentVO> list(int bno);

	int remove(int cno);

	int modify(CommentVO cvo);

}
