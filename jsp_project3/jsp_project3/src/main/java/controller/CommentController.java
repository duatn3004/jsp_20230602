package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CommentController.class);
    
    private int isOk;
    private CommentService csv;

    public CommentController() {
        csv = new CommentServiceImpl();
    }
    

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		log.info(uri);
		
		String pathUri = uri.substring("/cmt/".length());
		String path = pathUri;
		String pathVar = "";
		if(pathUri.contains("/")) {
			path = pathUri.substring(0,pathUri.lastIndexOf("/"));
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1);
		}
		
		log.info(pathUri);
		log.info(path);
		log.info(pathVar);
		
		switch (path) {
		case "post":
			try {
				StringBuffer sb = new StringBuffer();
				String line = "";
				//BufferedReader => int,String과 같이 file형태만 받는 타입
				BufferedReader br = request.getReader(); //댓글 객체
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info(">>>sb :"+sb.toString());
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				//DB에 넣는 과정
				CommentVO cvo = new CommentVO(bno,writer,content);
				
				isOk = csv.post(cvo);
				log.info(">>>post" + (isOk > 0 ? "성공":"실패"));
				
				//결과 전송
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(">>>> comment post > error");
				e.printStackTrace();
			}
			break;
			
		case "list":
			int bno = Integer.parseInt(pathVar);
			List<CommentVO> list = csv.getList(bno);
			log.info(">>>> comment list > DB ok");
			try {
				// JSON 형태로 변환해서 printWriter
				// json object를 담을 수 있는 배열을 만든것
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				// 배열을 담는 리스트를 만들기
				JSONArray jsonObjList = new JSONArray();
				for (int i = 0; i < list.size(); i++) {
					jsonObjArr[i] = new JSONObject(); // key : value
					jsonObjArr[i].put("cno", list.get(i).getCno()); // list에 get으로 i번째 배열을 가져와서 cno값을 가져와서 배열에 넣음
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("reg_date", list.get(i).getReg_date());

					// 하나씩 뽑은 데이터를 JsonObjList에 넣기
					jsonObjList.add(jsonObjArr[i]);
				}

				// 스트링 형태로 만들어서 보내기
				String jsonData = jsonObjList.toJSONString();
				log.info(">>> jsonObjList" + jsonObjList);

				// 보낼때 쓰는것 js의 resp
				PrintWriter out = response.getWriter();
				log.info(">>> jsonObjList" + jsonData);

				out.print(jsonData);

			} catch (Exception e) {
				log.info(">>>> comment list > error");
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				//쿼리스트링 ?로 보냈기 때문에 바로 int 값으로 받아도 됨
				int cno = Integer.parseInt(request.getParameter("cnoVal"));
				log.info(">>> cno >"+cno);
				isOk = csv.remove(cno);
				log.info(">>> remove >" + (isOk > 0 ? "성공" : "실패"));
				PrintWriter out = response.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {				
				StringBuffer sb = new StringBuffer();
				String line = "";
				BufferedReader br = request.getReader();
				while((line = br.readLine()) != null) {
					sb.append(line);
				}
				log.info(">>>sb : " + sb.toString());
				
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				int cno = Integer.parseInt(jsonObj.get("cno").toString());
				
				CommentVO cvo = new CommentVO(writer,content,cno);
				cvo.setCno(cno);
				cvo.setContent(content);
				log.info(">>>>> cvo "+cvo);
				isOk = csv.modify(cvo);
				log.info(">>>>modify" + (isOk > 0 ? "ok" : "fail"));
				PrintWriter out = response.getWriter(); // writer객체를 가져와서 응답객체로 보낼거임
				out.print(isOk);
				
			} catch (Exception e) {
				log.info("modify error~!!");
				e.printStackTrace();
			}
			break;
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
