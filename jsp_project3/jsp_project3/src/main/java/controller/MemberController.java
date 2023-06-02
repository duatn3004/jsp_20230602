package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;

@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// log 설정
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private MemberService msv;
	private int isOk;
	private String destPage;

	public MemberController() {
		msv = new MemberServiceImpl();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset-utf-8");

		String uri = request.getRequestURI();
		log.info(">>> uri : " + uri);
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info(">>> path : " + path);

		switch (path) {
		case "join":
			destPage = "/member/join.jsp";
			break;
		case "register":
			// jsp에서 가져온 파라미터 저장
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			int age = Integer.parseInt(request.getParameter("age"));

			// 파라미터로 mvo 객체 생성
			MemberVO mvo = new MemberVO(id, password, email, age);
			isOk = msv.register(mvo);
			log.info(">>> JOIN >" + (isOk > 0 ? "성공" : "실패"));
			destPage = "/";
			break;
			
		case "login":
			log.info(">>> login page 이동");
			destPage = "/member/login.jsp";
			break;
			
		case "login_auth":
			try {
				String id2 = request.getParameter("id");
				String password2 = request.getParameter("password");
				
				MemberVO mvo2 = new MemberVO(id2,password2);
				log.info(">>> login 요청!");
				MemberVO loginMvo = msv.login(mvo2);
				//같은 정보가 있으면 객체에 저장
				//객체를 세션에 저장해서 로그인 상태 유지
				
				if(loginMvo != null) {
					//세션 가져오기, 연결된 세션이 없다면 생성.
					HttpSession ses = request.getSession();
					ses.setAttribute("ses", loginMvo);
					//로그인 유지 시간, (10*60) 10분 유지하겠다.
					ses.setMaxInactiveInterval(10*60);
				}else {
					request.setAttribute("msg_login", 0);
				}
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "logout":
			try {
				HttpSession ses = request.getSession();
				MemberVO mvo2 = (MemberVO)ses.getAttribute("ses");
				String id2 = mvo2.getId();
				log.info(">>> login id : "+id2);
				
				//로그인정보(id)를 주고 마지막 로그인 시간 기록(update)
				isOk = msv.lastLogin(id2);
				log.info(">>> logout>"+(isOk > 0 ? "성공":"실패"));
				//세션의 값을 없앤다
				ses.invalidate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage = "/";
			break;
			
		case "modify":
			destPage ="/member/modify.jsp";
			break;
			
		case "edit":
			try {
				MemberVO mvo3 = new MemberVO(
						request.getParameter("id"),
						request.getParameter("password"),
						request.getParameter("email"),
						Integer.parseInt(request.getParameter("age")),
						request.getParameter("reg_date"),
						request.getParameter("last_login"));
				log.info(">>>"+mvo3);
				isOk=msv.modify(mvo3);
				log.info(">>> modify"+(isOk > 0 ? "성공":"실패"));
				log.info(">>> modify 완료, session 변경시작");
				if(isOk > 0) {
					request.setAttribute("mvo3", mvo3);
				}
				destPage = "login_auth";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "delete":
			try {
				HttpSession ses2 = request.getSession();
				MemberVO mvo4 = (MemberVO) ses2.getAttribute("ses");
				String id4 = mvo4.getId();
				isOk = msv.delete(id4);
				log.info(">>> delete>"+(isOk > 0 ? "성공":"실패"));
				//세션 로그아웃
				ses2.invalidate();
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "list":
			try {
				List<MemberVO> list = new ArrayList<MemberVO>();
				list= msv.list();
				log.info(">>> listSize"+list.size());
				request.setAttribute("list", list);
				System.out.println(">>> 리스트 성공");
				destPage="/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			

		}
		
		rdp= request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
