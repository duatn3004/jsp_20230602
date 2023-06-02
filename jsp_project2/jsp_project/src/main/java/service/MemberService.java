package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	int lastLogin(String id2);

	MemberVO login(MemberVO mvo2);

	int modify(MemberVO mvo3);

	int delete(String id4);

	List<MemberVO> list();

}
