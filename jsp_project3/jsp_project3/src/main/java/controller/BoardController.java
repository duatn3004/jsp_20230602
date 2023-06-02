package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	private BoardService bsv;
	//파일 경로를 저장할 변수
	private String savePath;
	private final String UTF8 = "utf-8";
       

    public BoardController() {
        bsv = new BoardServiceImpl(); 
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String uri = request.getRequestURI();
		log.info(">>> uri >" + uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(">>> path >" + path);
		
		switch (path) {
		case "register":
			destPage = "/board/register.jsp";
			break;
			
		case "insert":
			try {
				//파일을 업로드할 물리적인 경로를 설정
				savePath = getServletContext().getRealPath("/_fileUpload");
				log.info(savePath);
				File fileDir = new File(savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir);//파일의 저장위치를 담고있는 객체를 저장
				fileItemFactory.setSizeThreshold(2*1024-1024);//파일 저장을 위한 임시 메모리 용량
				log.info("check1");
				BoardVO bvo = new BoardVO();
				//multipart/form-data 형식으로 넘어온 request객체를 다루기 쉽게 변환해주는 역할
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				List<FileItem> itemList = fileUpload.parseRequest(request);
				for(FileItem item : itemList) {
					switch (item.getFieldName()) {
					case "title":
						bvo.setTitle(item.getString(UTF8));
						break;
					case "writer":
						bvo.setWriter(item.getString(UTF8));
						break;
					case "content":
						bvo.setContent(item.getString(UTF8));
						break;
					case "image_file":
						//이미지가 있는지 없는지 체크
						if(item.getSize()>0) {
							String fileName = item.getName().substring(item.getName().lastIndexOf("/")+1);
							fileName = System.currentTimeMillis()+"_"+fileName;
							log.info(">>>fileName : "+fileName);
							File uploadFilePath = new File(fileDir+File.separator+fileName);
							log.info("실제 파일경로 : "+uploadFilePath);
							
							//저장
							try {
								item.write(uploadFilePath);//자바 객체를 디스크에 쓰기
								bvo.setImage_file(fileName);
								//썸네일 작업 : 리스트 페이지에서 트레픽 과다사용 방지
								Thumbnails.of(uploadFilePath).size(75,75).toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								// TODO: handle exception
								log.info(">>> file writer on disk fail");
								e.printStackTrace();
							}
						}
						break;
					
					}
				}
				isOk=bsv.insert(bvo);
				log.info(">>> insert > " +(isOk > 0 ? "성공":"실패"));
				
				
//				String title = request.getParameter("title");
//				String writer = request.getParameter("writer");
//				String content = request.getParameter("content");
//				
//				BoardVO bvo = new BoardVO(title,writer,content);
//				
//				log.info(">>>"+bvo);
//				//insert, update, delete => 리턴타입 isOK
//				//select => BoardVO의 객체값 (여러개 리턴이면 List)
			} catch (Exception e) {
				e.printStackTrace();
			}
			destPage="page";
			break;
			
		case "list":
			try {
				List<BoardVO> list = new ArrayList<BoardVO>();
				list=bsv.list();
				request.setAttribute("list", list);
				log.info(">>> 게시판 리스트 성공");
				log.info(">>> list : "+list);
				destPage="/board/list.jsp";				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "page":
			try {
				int pageNo = 1;
				int qty = 10;
				String type="";
				String keyword="";
				if(request.getParameter("type") != null) {
					type = request.getParameter("type");
					keyword = request.getParameter("keyword");
					log.info(">>> type > "+ type + ">>> keyword > "+keyword);
				}
				if(request.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(request.getParameter("pageNo"));
					qty = Integer.parseInt(request.getParameter("qty"));
				}
				PagingVO pgvo = new PagingVO(pageNo,qty);
				pgvo.setType(type);
				pgvo.setKeyword(keyword);
				log.info(">>>pgvo > "+pgvo);
				//전체 페이지 개수
				int totCount = bsv.getTotal(pgvo);
				log.info("전체 페이지 개수 : "+totCount);
				//limit를 이용한 select List를 호출(startPage, qty)
				//한 페이지에 나와야 하는 리스트
				List<BoardVO> list = bsv.getPageList(pgvo);
				log.info(">>> list : "+list.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				request.setAttribute("pgh", ph);
				request.setAttribute("list", list);
				log.info("pageList 성공~!");
				destPage="/board/list.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "detail":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>"+bno);
				BoardVO bvo = bsv.detail(bno);
				log.info(">>> detail >" + bvo);
				request.setAttribute("bvo", bvo);
				destPage="/board/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>"+bno);
				BoardVO bvo = bsv.detail(bno);
				log.info(">>> detail >"+bvo);
				request.setAttribute("bvo", bvo);
				
				destPage = "/board/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
		case "edit":
			try {
//				int bno, String title, String content
				BoardVO bvo = new BoardVO(Integer.parseInt(request.getParameter("bno")),
						request.getParameter("title"),
						request.getParameter("content"));
				log.info(">>>"+bvo);
				isOk = bsv.modify(bvo);
				log.info(">>> modify >" + (isOk > 0 ? "성공":"실패"));
				destPage="list";
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
			
		case "remove":
			try {
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info(">>>"+bno);
				isOk = bsv.remove(bno);
				log.info(">>> modify >"+(isOk > 0 ? "성공":"실패"));
				destPage="list";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		}
		rdp = request.getRequestDispatcher(destPage);
		rdp.forward(request, response);
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
