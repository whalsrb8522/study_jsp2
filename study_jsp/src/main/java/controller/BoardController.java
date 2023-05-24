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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.PagingHandler;
import service.BoardService;
import service.BoardServiceImpl;

@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	private RequestDispatcher rdp;
	private BoardService bsv;
	private String destPage;
	private int isOk;
	
	private BoardVO bvo;
	private int bno;
	private String title;
	private String writer;
	private String content;
	
    public BoardController() {
    	bsv = new BoardServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/") + 1);
		log.info("* uri : " + uri);		
		log.info("* path : " + path);
		
		switch (path) {
//		case "list":
//			List<BoardVO> list = new ArrayList<BoardVO>();
//			
//			list = bsv.list();
//			req.setAttribute("list", list);
//			
//			destPage = "/board/list.jsp";
//			
//			break;
		case "register_s1":
			destPage = "/board/register.jsp";
			
			break;
		case "register_s2":
			title = req.getParameter("title");
			writer = req.getParameter("writer");
			content =req.getParameter("content");
			bvo = new BoardVO(title, writer, content);
			
			isOk = bsv.register(bvo);
			log.info("* 게시글 등록 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/page";
			
			break;
		case "detail":
			bno = Integer.parseInt(req.getParameter("bno"));
			bvo = bsv.detail(bno);
			
			req.setAttribute("bvo", bvo);
		
			destPage ="/board/detail.jsp";
			
			break;
		case "modify_s1":
			bno = Integer.parseInt(req.getParameter("bno"));
			bvo = bsv.detail(bno);
			
			req.setAttribute("bvo", bvo);
		
			destPage ="/board/modify.jsp";
			
			break;
		case "modify_s2":
			bno = Integer.parseInt(req.getParameter("bno"));
			title = req.getParameter("title");
			content = req.getParameter("content");
			bvo = new BoardVO(bno, title, content);
			
			isOk = bsv.modify(bvo);
			log.info("* 게시글 수정 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/detail?bno = " + bno; 
			
			break;
		case "delete":
			bno = Integer.parseInt(req.getParameter("bno"));
			
			isOk = bsv.delete(bno);
			log.info("* 게시글 삭제 : " + (isOk > 0 ? "성공" : "실패"));
			
			destPage = "/brd/page";
			
			break;
		case "page":
			try {
				int pageNo = 1;
				int qty = 10;
				
				if(req.getParameter("pageNo") != null) {
					pageNo = Integer.parseInt(req.getParameter("pageNo"));
					qty = Integer.parseInt(req.getParameter("qty"));
				}
				
				PagingVO pgvo = new PagingVO(pageNo,qty);
				
				//전체 페이지 개수
				int totCount = bsv.getTotal();
				log.info("전체 페이지 개수 : " + totCount);
				
				//limit를 이용한 select List를 호출 (startPage, qty
				// 한페이지에 나와야 하는 리스트
				List<BoardVO> boardList = bsv.getPageList(pgvo); 
				log.info(">>>> list : " + boardList.size());
				PagingHandler ph = new PagingHandler(pgvo, totCount);
				req.setAttribute("pgh", ph);
				req.setAttribute("list", boardList);
				log.info("pageList 성공~!!");
				destPage="/board/list.jsp";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		}
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// doGet(request, response);
		service(request, response);
	}
}
